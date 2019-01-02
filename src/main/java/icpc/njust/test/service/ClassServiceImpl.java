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
    //String classid, String classname, String teacherid
    public List<String> addClass(Object[] argument) {
        String classid=argument[0].toString();
        String classname=argument[1].toString();
        String teacherid=argument[2].toString();
        classTeacherDao.create(classid,teacherid,classname);
        List<String> result=new ArrayList<>();
        result.add("Success");
        return result;
    }

    @Override
    //String classid, String teacherid
    public List<String> changeteacher(Object[] argument) {
        String classid=argument[0].toString();
        String teacherid=argument[1].toString();
        classTeacherDao.updateTeacher(classid,teacherid);
        List<String> result=new ArrayList<>();
        result.add("Success");
        return result;
    }

    @Override
    //String classid
    public List<String> deleteclass(Object[] argument) {
        String classid=argument[0].toString();
        classStudentDao.clearByClass(classid);
        warninginfoDao.clearByClass(classid);
        classTeacherDao.delete(classid);
        List<String> result=new ArrayList<>();
        result.add("Success");
        return result;
    }

    @Override
    public List<String> clear() {
        //先扔着，反正估计到最后也用不着。
        List<String> result=new ArrayList<>();
        result.add("Success");
        return result;
    }

    @Override
    //String classid, String studentid
    public List<String> addstudent(Object[] argument) {
        String classid=argument[0].toString();
        String studentid=argument[1].toString();
        if(classTeacherDao.find(classid)==null){
            System.out.println("classid为"+classid+"的课不存在");
        }
        else{
            classStudentDao.create(studentid,classid);
        }
        List<String> result=new ArrayList<>();
        result.add("Success");
        return result;
    }

    @Override
    //String classid
    public ClassTeacherEntity findclass(Object[] argument) {
        String classid=argument[0].toString();
        return classTeacherDao.find(classid);
    }

    @Override
    //String teacherid
    public List<ClassTeacherEntity> findByTeacher(Object[] argument) {
        String teacherid=argument[0].toString();
        return classTeacherDao.findByTeacher(teacherid);
    }

    @Override
    //String studentid
    public List<ClassTeacherEntity> findByStudent(Object[] argument) {
        String studentid=argument[0].toString();
        List<ClassStudentEntity> classlist =classStudentDao.findByStudent(studentid);
        List<ClassTeacherEntity> mylist = new ArrayList<>();
        for (ClassStudentEntity classStudentEntity : classlist) {
            mylist.add(classTeacherDao.find(classStudentEntity.getClassid()));
        }
        return mylist;
    }
}
