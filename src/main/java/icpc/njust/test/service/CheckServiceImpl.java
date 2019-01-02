package icpc.njust.test.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import icpc.njust.test.Utils.CompareFaceUtil;
import icpc.njust.test.Utils.FindFaceUtil;
import icpc.njust.test.Utils.WarnUtil;
import icpc.njust.test.repository.*;
import icpc.njust.test.table.ClassStudentEntity;
import icpc.njust.test.table.StudentstatusEntity;
import icpc.njust.test.table.WarninginfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
@Service("CheckService")
public class CheckServiceImpl implements CheckService {
    private final ClassStudentDao classStudentDao;
    private final ClassTeacherDao classTeacherDao;
    private final PhotostorageDao photostorageDao;
    private final StudentstatusDao studentstatusDao;
    private final WarninginfoDao warninginfoDao;
    private final UserinfoDao userinfoDao;
    @Autowired
    public CheckServiceImpl(ClassStudentDao classStudentDao, ClassTeacherDao classTeacherDao, PhotostorageDao photostorageDao,
                            StudentstatusDao studentstatusDao, WarninginfoDao warninginfoDao, UserinfoDao userinfoDao) {
        this.classStudentDao=classStudentDao;
        this.classTeacherDao=classTeacherDao;
        this.photostorageDao=photostorageDao;
        this.studentstatusDao = studentstatusDao;
        this.warninginfoDao = warninginfoDao;
        this.userinfoDao = userinfoDao;
    }

    @Override
    //String classid, String image_base64
    public List<String> signin(Object[] argument) throws Exception {
        classTeacherDao.addClasstime(argument[0].toString());
        String classcnt=classTeacherDao.find(argument[0].toString()).getClasstimes();
        List<ClassStudentEntity> studentlist=classStudentDao.findByClass(argument[0].toString());
        int totstudents = studentlist.size();//应到总人数

        String str = FindFaceUtil.detectFace(argument[1].toString());
        //System.out.print(str);
        JSONObject json = JSON.parseObject(str);
        JSONArray faces = json.getJSONArray("faces");
        int totfaces=faces.size();//照片总人数


        for(int i=0; i<totstudents; i++){
            boolean flag=false;
            String studenttoken=photostorageDao.find(studentlist.get(i).getId()).getFacetoken();
            for(int j=0; j<totfaces; j++){
                JSONObject comparejson=JSON.parseObject(CompareFaceUtil.compare(studenttoken,faces.getJSONObject(j).getString("face_token")));
                double confidence=Double.valueOf(comparejson.getString("confidence"));
                double availableconfidence=Double.valueOf(comparejson.getJSONObject("thresholds").getString("1e-5"));
                //误识率为十万分之一的置信度阈值
                if(confidence>=availableconfidence){
                    flag=true;
                    break;
                }
            }
            if(flag){
                studentstatusDao.create(studentlist.get(i).getId(),argument[0].toString(),classcnt,"attended","0");
            }
            else{
                studentstatusDao.create(studentlist.get(i).getId(),argument[0].toString(),classcnt,"absent","1");
            }
        }
        List<String> result=new ArrayList<>();
        result.add("Success");
        return result;
    }

    @Override
    //String classid, String image_base64
    public List<String> checkstatus(Object[] argument) throws Exception {
        classTeacherDao.addClasstime(argument[0].toString());
        String classcnt=classTeacherDao.find(argument[0].toString()).getClasstimes();
        List<ClassStudentEntity> studentlist=classStudentDao.findByClass(argument[0].toString());
        int totstudents = studentlist.size();//应到总人数

        String str = FindFaceUtil.detectFace(argument[1].toString());
        //System.out.print(str);
        JSONObject json = JSON.parseObject(str);
        JSONArray faces = json.getJSONArray("faces");
        int totfaces=faces.size();//照片总人数


        for(int i=0; i<totstudents; i++){
            boolean flag=false;
            String studenttoken=photostorageDao.find(studentlist.get(i).getId()).getFacetoken();
            for(int j=0; j<totfaces; j++){
                JSONObject comparejson=JSON.parseObject(CompareFaceUtil.compare(studenttoken,faces.getJSONObject(j).getString("face_token")));
                double confidence=Double.valueOf(comparejson.getString("confidence"));
                double availableconfidence=Double.valueOf(comparejson.getJSONObject("thresholds").getString("1e-5"));
                //误识率为十万分之一的置信度阈值
                if(confidence>=availableconfidence){
                    flag=true;
                    break;
                }
            }
            String studentid=studentlist.get(i).getId();
            StudentstatusEntity studentstatusEntity=studentstatusDao.find(argument[0].toString(),classcnt,studentid).get(0);
            if(flag){//匹配上了
                if(studentstatusEntity.getAttend().compareTo("absent")==0){
                    studentstatusEntity.setAttend("belate");
                }
                studentstatusDao.update(studentstatusEntity.getRecordid(),studentid,argument[0].toString(),classcnt,studentstatusEntity.getAttend(),studentstatusEntity.getWarningnumber());
            }
            else{//没匹配上
                String warningnumber=String.valueOf(Integer.valueOf(studentstatusEntity.getWarningnumber())+1);
                studentstatusDao.update(studentstatusEntity.getRecordid(),studentid,argument[0].toString(),classcnt,studentstatusEntity.getAttend(),warningnumber);
                WarninginfoEntity warninginfoEntity = new WarninginfoEntity();
                warninginfoEntity.setClasscnt(classcnt);
                warninginfoEntity.setClassid(argument[0].toString());
                warninginfoEntity.setId(studentid);
                Date now=new Date();
                warninginfoEntity.setTime(now.toString());
                warninginfoEntity.setWarningcontent("请不要上课开小差23333");
                warninginfoDao.create(studentid,argument[0].toString(),classcnt,"请不要上课开小差23333",now.toString());
                WarnUtil.warn(warninginfoEntity);
            }
        }
        List<String> result=new ArrayList<>();
        result.add("Success");
        return result;
    }

    @Override
    //String classid, String classcnt
    public List findstatusByOneclass(Object[] argument) {
        String classid=argument[0].toString();
        String classcnt=argument[1].toString();
        List<StudentstatusEntity> studentstatuslist = studentstatusDao.findByOneClass(classid,classcnt);
        List<MyStudentStatus> mylist = new ArrayList<MyStudentStatus>();
        for(int i=0; i<studentstatuslist.size(); i++){
            MyStudentStatus myStudentStatus = new MyStudentStatus();
            myStudentStatus.setId(studentstatuslist.get(i).getStudentid());
            myStudentStatus.setAttend(studentstatuslist.get(i).getAttend());
            myStudentStatus.setWarningnumber(studentstatuslist.get(i).getWarningnumber());
            myStudentStatus.setName(userinfoDao.find(studentstatuslist.get(i).getStudentid()).getName());
            mylist.add(myStudentStatus);
        }
        return mylist;
    }

    @Override
    //String classid, String studentid
    public List<StudentstatusEntity> findstatusByStudent(Object[] argument) {
        String classid = argument[0].toString();
        String studentid = argument[1].toString();
       return studentstatusDao.findByClassStudent(classid,studentid);
    }

    @Override
    //String classid, String classcnt
    public List<WarninginfoEntity> findwarningByOneclass(Object[] argument) {
        String classid=argument[0].toString();
        String classcnt=argument[1].toString();
        return warninginfoDao.findByOneClass(classid,classcnt);
    }

    @Override
    //String classid, String studentid
    public List<WarninginfoEntity> findwarningByStudent(Object[] argument) {
        String classid=argument[0].toString();
        String studentid=argument[1].toString();
        return warninginfoDao.findByClassStudent(classid,studentid);
    }
}

