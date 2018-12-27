package icpc.njust.test.repository;

import icpc.njust.test.table.ClassStudentEntity;
import icpc.njust.test.table.ClassTeacherEntity;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public interface ClassTeacherDao {
    void create(String classid, String id, String classname);//增加
    void delete(String classid);//删除
    List<ClassTeacherEntity> showall();//显示所有
    ClassTeacherEntity find(String classid);//显示一门课
    List<ClassTeacherEntity> findByTeacher(String teacherid);//显示老师的所有课
    void update(String classid, String id, String classname, String classtimes);//全部update
    void updateTeacher(String classid, String teacherid);//修改老师
    void updateClassname(String classid, String classname);//修改课程名
    void addClasstime(String classid);//增加一次上课次数
}
