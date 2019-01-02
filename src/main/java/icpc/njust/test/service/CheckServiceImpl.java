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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void signin(String classid, String image_base64) throws Exception {
        classTeacherDao.addClasstime(classid);
        String classcnt=classTeacherDao.find(classid).getClasstimes();
        List<ClassStudentEntity> studentlist=classStudentDao.findByClass(classid);
        int totstudents = studentlist.size();//应到总人数

        String str = FindFaceUtil.detectFace(image_base64);
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
                studentstatusDao.create(studentlist.get(i).getId(),classid,classcnt,"attended","0");
            }
            else{
                studentstatusDao.create(studentlist.get(i).getId(),classid,classcnt,"absent","1");
            }
        }

        return;
    }

    @Override
    public void checkstatus(String classid, String image_base64) throws Exception {
        classTeacherDao.addClasstime(classid);
        String classcnt=classTeacherDao.find(classid).getClasstimes();
        List<ClassStudentEntity> studentlist=classStudentDao.findByClass(classid);
        int totstudents = studentlist.size();//应到总人数

        String str = FindFaceUtil.detectFace(image_base64);
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
            StudentstatusEntity studentstatusEntity=studentstatusDao.find(classid,classcnt,studentid).get(0);
            if(flag){//匹配上了
                if(studentstatusEntity.getAttend().compareTo("absent")==0){
                    studentstatusEntity.setAttend("belate");
                }
                studentstatusDao.update(studentstatusEntity.getRecordid(),studentid,classid,classcnt,studentstatusEntity.getAttend(),studentstatusEntity.getWarningnumber());
            }
            else{//没匹配上
                String warningnumber=String.valueOf(Integer.valueOf(studentstatusEntity.getWarningnumber())+1);
                studentstatusDao.update(studentstatusEntity.getRecordid(),studentid,classid,classcnt,studentstatusEntity.getAttend(),warningnumber);
                WarninginfoEntity warninginfoEntity = new WarninginfoEntity();
                warninginfoEntity.setClasscnt(classcnt);
                warninginfoEntity.setClassid(classid);
                warninginfoEntity.setId(studentid);
                Date now=new Date();
                warninginfoEntity.setTime(now.toString());
                warninginfoEntity.setWarningcontent("请不要上课开小差23333");
                warninginfoDao.create(studentid,classid,classcnt,"请不要上课开小差23333",now.toString());
                WarnUtil.warn(warninginfoEntity);
            }
        }

        return;
    }

    @Override
    public List findstatusByOneclass(String classid, String classcnt) {
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
    public List<StudentstatusEntity> findstatusByStudent(String classid, String studentid) {
       return studentstatusDao.findByClassStudent(classid,studentid);
    }

    @Override
    public List<WarninginfoEntity> findwarningByOneclass(String classid, String classcnt) {
        return warninginfoDao.findByOneClass(classid,classcnt);
    }

    @Override
    public List<WarninginfoEntity> findwarningByStudent(String classid, String studentid) {
        return warninginfoDao.findByClassStudent(classid,studentid);
    }
}

class MyStudentStatus{
    private String id;
    private String name;
    private String attend;
    private String warningnumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getWarningnumber() {
        return warningnumber;
    }

    public void setWarningnumber(String warningnumber) {
        this.warningnumber = warningnumber;
    }
}
