package icpc.njust.test.repository;

import icpc.njust.test.table.ClassStudentEntity;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public interface ClassStudentDao {
    void addChoose(String chooseid,String id,String classid);  //增加
    void deleteChoose(String chooseid); //删除
    void deleteChoose(String id, String classid);//删除一个选择信息
//    void clearByClass(String classid);//删除一门课的选课信息
//    void clearByStudent(String id);//删除一个学生的选课信息
    ClassStudentEntity findByChooseid(String chooseid);//根据chooseid查询
    List<ClassStudentEntity> findByClass(String classid); //显示一门课的选课情况
    List<ClassStudentEntity> findByStudent(String id); //显示一个学生有哪些课
}
