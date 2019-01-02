package icpc.njust.test.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import icpc.njust.test.Utils.AddFaceUtil;
import icpc.njust.test.Utils.FindFaceUtil;
import icpc.njust.test.repository.*;
import icpc.njust.test.table.UserinfoEntity;
import icpc.njust.test.table.WarninginfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("UderinfoService")
public class UserinfoServiceImpl implements UserinfoService {
    private final UserinfoDao userinfoDao;
    private final ClassStudentDao classStudentDao;
    private final ClassTeacherDao classTeacherDao;
    private final PhotostorageDao photostorageDao;
    private final StudentstatusDao studentstatusDao;
    private final WarninginfoDao warninginfoDao;
    @Autowired
    public UserinfoServiceImpl(UserinfoDao userinfoDao,ClassStudentDao classStudentDao, ClassTeacherDao classTeacherDao,
                                PhotostorageDao photostorageDao, StudentstatusDao studentstatusDao, WarninginfoDao warninginfoDao) {
        this.userinfoDao = userinfoDao;
        this.classStudentDao=classStudentDao;
        this.classTeacherDao=classTeacherDao;
        this.photostorageDao=photostorageDao;
        this.studentstatusDao=studentstatusDao;
        this.warninginfoDao=warninginfoDao;
    }

    @Override
    public void adduser(String id, String name, String identity, String phone, String email, String school, String academy) {
//        if(identity.compareTo("teacher")!=0 && identity.compareTo("student")!=0){
//                    System.out.println("身份不正确！");
//                    return;
//                }
        userinfoDao.create(id,name,phone,email,school,academy,identity);
        return;
    }

    @Override
    public boolean addPhoto(String id, String image_base64) throws Exception {
        String str = FindFaceUtil.detectFace(image_base64);
        //System.out.print(str);
        JSONObject json = JSON.parseObject(str);
        try {
            JSONArray faces = json.getJSONArray("faces");
            if ("[]".equals(faces)) {
                return false;
            }
            JSONObject josnToken = faces.getJSONObject(0);
            String facetoken = josnToken.getString("face_token");
            AddFaceUtil.add(facetoken,"classroom");
            //图片太大了，本地数据库存不下，先别存了，也能跑！
            photostorageDao.create(id,null,facetoken);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public void updatePhoto(String id, String image_base64) {

    }

    @Override
    public void updateuser(String id, String name, String identity, String phone, String email, String school, String academy) {
        UserinfoEntity userinfoEntity=finduser(id);
//        if(identity!=null && identity.compareTo("teacher")!=0 && identity.compareTo("student")!=0){
//            System.out.println("身份不正确！");
//            return;
//        }
        if(name==null) name=userinfoEntity.getName();
        if(identity==null) identity=userinfoEntity.getIdentity();
        if(phone==null) phone=userinfoEntity.getPhone();
        if(email==null) email=userinfoEntity.getEmail();
        if(school==null) school=userinfoEntity.getSchool();
        if(academy==null) academy=userinfoEntity.getAcademy();
        userinfoDao.update(id,name,phone,email,school,academy,identity);
        return;
    }

    @Override
    public void deleteuser(String id) {
        UserinfoEntity userinfoEntity=userinfoDao.find(id);
        if(userinfoEntity.getIdentity().compareTo("teacher")==0){

        }
        else if(userinfoEntity.getIdentity().compareTo("student")==0){
            classStudentDao.clearByStudent(id);
            photostorageDao.delete(id);
            studentstatusDao.clearByStudent(id);
            warninginfoDao.clearByStudent(id);
        }
        userinfoDao.delete(id);
    }

    @Override
    public UserinfoEntity finduser(String id) {
        return userinfoDao.find(id);
    }
}
