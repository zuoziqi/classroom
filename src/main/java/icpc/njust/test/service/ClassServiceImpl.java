package icpc.njust.test.service;

import icpc.njust.test.repository.ClassStudentDao;
import icpc.njust.test.repository.ClassTeacherDao;
import icpc.njust.test.repository.WarninginfoDao;
import icpc.njust.test.table.ClassStudentEntity;
import icpc.njust.test.table.ClassTeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ClassService")
public class ClassServiceImpl implements ClassService {
    private final ClassTeacherDao classTeacherDao;
    private final ClassStudentDao classStudentDao;
    private final WarninginfoDao warninginfoDao;
    @Autowired
    public ClassServiceImpl(ClassTeacherDao classTeacherDao, ClassStudentDao classStudentDao, WarninginfoDao warninginfoDao) {
        this.classTeacherDao=classTeacherDao;
        this.classStudentDao=classStudentDao;
        this.warninginfoDao=warninginfoDao;
    }

    @Override
    public void addclass(String classid, String classname, String teacherid) {
        classTeacherDao.create(classid,teacherid,classname);
    }

    @Override
    public void changeteacher(String classid, String teacherid) {
        classTeacherDao.updateTeacher(classid,teacherid);
    }

    @Override
    public void deleteclass(String classid) {
        classStudentDao.clearByClass(classid);
        warninginfoDao.cleatByClass(classid);
        classTeacherDao.delete(classid);
    }

    @Override
    public void clear() {
        //先扔着，反正估计到最后也用不着。
    }

    @Override
    public void addstudent(String classid, String studentid) {
        if(classTeacherDao.find(classid)==null){
            System.out.println("classid为"+classid+"的课不存在");
        }
        else{
            classStudentDao.create(studentid,classid);
        }
    }

    @Override
    public ClassTeacherEntity findclass(String classid) {
        return classTeacherDao.find(classid);
    }

    @Override
    public List<ClassTeacherEntity> findByTeacher(String teacherid) {
        return classTeacherDao.findByTeacher(teacherid);
    }

    @Override
    public List<ClassTeacherEntity> findByStudent(String studentid) {
        List<ClassStudentEntity> classlist =classStudentDao.findByStudent(studentid);
        List<ClassTeacherEntity> mylist = new ArrayList<>();
        for(int i=0; i<classlist.size(); i++){
            mylist.add(classTeacherDao.find(classlist.get(i).getClassid()));
        }
        return mylist;
    }
}
