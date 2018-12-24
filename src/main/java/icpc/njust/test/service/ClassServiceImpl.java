package icpc.njust.test.service;

import icpc.njust.test.repository.ClassStudentDao;
import icpc.njust.test.repository.ClassTeacherDao;
import icpc.njust.test.table.ClassTeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ClassService")
public class ClassServiceImpl implements ClassService {
    private final ClassTeacherDao classTeacherDao;
    private final ClassStudentDao classStudentDao;
    @Autowired
    public ClassServiceImpl(ClassTeacherDao classTeacherDao, ClassStudentDao classStudentDao) {
        this.classTeacherDao=classTeacherDao;
        this.classStudentDao=classStudentDao;
    }

    @Override
    public void addclass(String classid, String classname, String teacherid) {

    }

    @Override
    public void changeteacher(String classid, String teacherid) {

    }

    @Override
    public void deleteclass(String classid) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void addstudent(String classid, String studentid) {

    }

    @Override
    public ClassTeacherEntity findclass(String classid) {
        return null;
    }

    @Override
    public List<ClassTeacherEntity> findByTeacher(String teacherid) {
        return null;
    }

    @Override
    public List<ClassTeacherEntity> findByStudent(String student) {
        return null;
    }
}
