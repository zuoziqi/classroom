package icpc.njust.test.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import icpc.njust.test.MessageTemplate.UserInfo;
import icpc.njust.test.MessageTemplate.UserPrivilege;
import icpc.njust.test.Utils.AddFaceUtil;
import icpc.njust.test.Utils.FindFaceUtil;
import icpc.njust.test.repository.*;
import icpc.njust.test.table.UserinfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sun.plugin2.message.Message;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
@Service("UserinfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Resource(name="jmsTemplate")
    private JmsTemplate jmsTemplate;
    private final MessageQueueService messageQueueService;
    private final UserinfoDao userinfoDao;
    private final ClassStudentDao classStudentDao;
    private final ClassTeacherDao classTeacherDao;
    private final PhotostorageDao photostorageDao;
    private final StudentstatusDao studentstatusDao;
    private final WarninginfoDao warninginfoDao;
    @Autowired
    public UserInfoServiceImpl(UserinfoDao userinfoDao, ClassStudentDao classStudentDao, ClassTeacherDao classTeacherDao,
                               PhotostorageDao photostorageDao, StudentstatusDao studentstatusDao, WarninginfoDao warninginfoDao, MessageQueueService messageQueueService) {
        this.userinfoDao = userinfoDao;
        this.classStudentDao=classStudentDao;
        this.classTeacherDao=classTeacherDao;
        this.photostorageDao=photostorageDao;
        this.studentstatusDao=studentstatusDao;
        this.warninginfoDao=warninginfoDao;
        this.messageQueueService = messageQueueService;
    }

    @Override
    //String id, String name, String identity, String phone, String email, String school, String academy
    public List<String> adduser(Object[] argument) {
        String id=argument[0].toString();
        String name=argument[1].toString();
        String phone=argument[2].toString();
        String email=argument[3].toString();
        String school=argument[4].toString();
        String academy=argument[5].toString();
        String identity=argument[6].toString();
        userinfoDao.create(id,name,phone,email,school,academy,identity);
        try {
            Destination target=jmsTemplate.getConnectionFactory().createConnection()
                    .createSession(false, Session.AUTO_ACKNOWLEDGE).createQueue("User.Registry");
            UserInfo info=new UserInfo();
            info.username=id;
            info.password=id;
            info.privileges= UserPrivilege.SystemUser.toString();
            messageQueueService.sendMessage(target,JSON.toJSONString(info));
        } catch (JMSException e) {
            e.printStackTrace();
        }
        List<String> result=new ArrayList<>();
        result.add("Success");
        return result;
    }

    @Override
    //String id, String image_base64
    public boolean addPhoto(Object[] argument) throws Exception {
        String id=argument[0].toString();
        String image_base64=argument[1].toString();
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
    //String id, String image_base64
    public List<String> updatePhoto(Object[] argument) {
        String id=argument[0].toString();
        String image_base64=argument[1].toString();
        List<String> result=new ArrayList<>();
        result.add("Success");
        return result;
    }

    @Override
    //String id, String name, String identity, String phone, String email, String school, String academy
    public List<String> updateuser(Object[] argument) {
        String id=argument[0].toString();
        String name=argument[1].toString();
        String phone=argument[2].toString();
        String email=argument[3].toString();
        String school=argument[4].toString();
        String academy=argument[5].toString();
        String identity=argument[6].toString();
        Object[] tmp=new Object[]{id};
        UserinfoEntity userinfoEntity=finduser(tmp);
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
        List<String> result=new ArrayList<>();
        result.add("Success");
        return result;
    }

    @Override
    //String id
    public List<String> deleteuser(Object[] argument) {
        String id=argument[0].toString();
        UserinfoEntity userinfoEntity=userinfoDao.find(id);
        if (userinfoEntity.getIdentity().compareTo("teacher") != 0) {
            if(userinfoEntity.getIdentity().compareTo("student")==0){
                classStudentDao.clearByStudent(id);
                photostorageDao.delete(id);
                studentstatusDao.clearByStudent(id);
                warninginfoDao.clearByStudent(id);
            }
        }
        userinfoDao.delete(id);
        List<String> result=new ArrayList<>();
        result.add("Success");
        return result;
    }

    @Override
    //String id
    public UserinfoEntity finduser(Object[] argument) {
        String id=argument[0].toString();
        return userinfoDao.find(id);
    }
}
