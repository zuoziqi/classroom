package icpc.njust.test.service;

import icpc.njust.test.repository.*;
import icpc.njust.test.table.UserinfoEntity;
import icpc.njust.test.table.WarninginfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //if(identity.compareTo("teacher")!=0 && identity.compareTo("student")!=0) return;
        userinfoDao.create(id,name,phone,email,school,academy,identity);
        return;
    }

    @Override
    public void updateuser(String id, String name, String identity, String phone, String email, String school, String academy) {
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
