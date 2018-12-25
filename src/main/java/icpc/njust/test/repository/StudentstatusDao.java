package icpc.njust.test.repository;

import icpc.njust.test.table.StudentstatusEntity;
import icpc.njust.test.table.WarninginfoEntity;

import java.util.List;

public interface StudentstatusDao {
    void create(String studentid, String classid, String classcnt, String attend, String warningnumber);//增加
    void delete(String recordid);//删除
    void clear(String classid, String classcnt, String studentid);//删除
    void clearByStudent(String studentid);
    void clearByClass(String classid);
    List<StudentstatusEntity> showall();//显示所有
    StudentstatusEntity find(String id);//根据id查询
    List<StudentstatusEntity> findByClassStudent(String classid, String studentid);//根据课和学生查询
    List<StudentstatusEntity> findByOneClass(String classid, String classcnt);
    void update(String recordid,String studentid, String classid, String classcnt, String attend, String warningnumber);//更新

}
