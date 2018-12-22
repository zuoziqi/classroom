package icpc.njust.test.repository;

import icpc.njust.test.table.PersonalinformationEntity;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public interface PersonalinformationDao {
    void  addinfo(String id,String name,String phone,String email,String school,String academy,String identity);//增加
    void  deleteinfo(String id);//删除
    List<PersonalinformationEntity> showall();//显示所有
    PersonalinformationEntity findinfo(String id);//根据id查询
    void changeinfo(String id,String name,String phone,String email,String school,String academy,String identity);//修改
}
