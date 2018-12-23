package icpc.njust.test.repository;

import icpc.njust.test.table.UserinfoEntity;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public interface UserinfoDao {
    void  create(String id,String name,String phone,String email,String school,String academy,String identity);//增加
    void  delete(String id);//删除
    List<UserinfoEntity> showall();//显示所有
    UserinfoEntity find(String id);//根据id查询
    void update(String id,String name,String phone,String email,String school,String academy,String identity);//更新
    void updateInfo(String id,String phone, String email, String school, String academy);//更新个人信息
}
