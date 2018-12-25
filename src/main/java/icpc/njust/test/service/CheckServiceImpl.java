package icpc.njust.test.service;

import icpc.njust.test.repository.StudentstatusDao;
import icpc.njust.test.repository.WarninginfoDao;
import icpc.njust.test.table.StudentstatusEntity;
import icpc.njust.test.table.WarninginfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CheckService")
public class CheckServiceImpl implements CheckService {
    private final StudentstatusDao studentstatusDao;
    private final WarninginfoDao warninginfoDao;
    @Autowired
    public CheckServiceImpl(StudentstatusDao studentstatusDao, WarninginfoDao warninginfoDao) {
        this.studentstatusDao = studentstatusDao;
        this.warninginfoDao = warninginfoDao;
    }

    @Override
    public void signin(String classid, String image_base64, String time) {

    }

    @Override
    public void checkstatus(String classid, String image_base64, String time) {

    }

    @Override
    public List<StudentstatusEntity> findstatusByOneclass(String classid, String classcnt) {
        return studentstatusDao.findByOneClass(classid,classcnt);
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
