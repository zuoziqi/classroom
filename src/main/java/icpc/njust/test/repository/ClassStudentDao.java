package icpc.njust.test.repository;

import icpc.njust.test.table.ClassStudentEntity;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public interface ClassStudentDao {
    void addClassStudent(String chooseid,String id,String classid);  //增加
    void deleteClsaaStudent(String chooseid); //删除
    List<ClassStudentEntity> showall(); //显示所有表中信息
    ClassStudentEntity findClassStudent(String chooseid);//根据chooseid查询
    void changeClassStudent(String chooseid,String id,String classid);//修改
}
