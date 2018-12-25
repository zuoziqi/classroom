package icpc.njust.test.repository;

import icpc.njust.test.table.WarninginfoEntity;

import java.util.List;

/**
 * Created by DELL on 2018/12/23.
 */
public interface WarninginfoDao {
    void create(String studentid, String classid, String classcnt, String warningcontent, String time);//增加
    void delete(String warningid);//删除
    void clear(String classid, String classcnt, String id);
    void cleatByClass(String classid);
    void clearByStudent(String studentid);
    List<WarninginfoEntity> showall();//显示所有
    WarninginfoEntity find(String warningid);//根据id查询
    List<WarninginfoEntity> findByOneClass(String classid, String classcnt);
    List<WarninginfoEntity> findByClassStudent(String classid, String studentid);
    List<WarninginfoEntity> findByOneClassStudent(String classid, String classcnt, String studentid);//根据课和学生查询
    void update(String warningid, String studentid, String classid, String classcnt, String warningcontent, String time);//更新
}
