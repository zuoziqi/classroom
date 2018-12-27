package icpc.njust.test.repository;

import icpc.njust.test.table.ClassStudentEntity;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public interface ClassStudentDao {
    void create(String id, String classid);  // 创建
    void delete(String chooseid); //删除
    void delete(String id, String classid);//删除一个选择信息
    void clearByStudent(String id);
    void clearByClass(String classid);
//    void clearByClass(String classid);//删除一门课的选课信息
//    void clearByStudent(String id);//删除一个学生的选课信息
    List<ClassStudentEntity> showall();//显示所有
    ClassStudentEntity find(String chooseid);//根据chooseid查询
    List<ClassStudentEntity> findByClass(String classid); //显示一门课的选课情况
    List<ClassStudentEntity> findByStudent(String id); //显示一个学生有哪些课
}
