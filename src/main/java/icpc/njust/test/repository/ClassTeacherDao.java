package icpc.njust.test.repository;

import icpc.njust.test.table.ClassStudentEntity;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public interface ClassTeacherDao {
    void addClassTeacher(String classid,String id,String classname);//增加
    void deleteClassTeacher(String classid);//删除
    List<ClassStudentEntity> showall();//显示所有
    ClassStudentEntity findClassTeacher(String classid);//根据classid查询
    void changeClassTeacher(String classid,String id,String classname,String classtimes);//修改
}
