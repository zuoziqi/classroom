package icpc.njust.test.service;

import icpc.njust.test.table.UserinfoEntity;

import java.util.List;

public interface UserInfoService {
    List<String> adduser(Object[] argument);
    boolean addPhoto(Object[] argument) throws Exception;//增加照片信息，得有这个学生才能加,返回是否成功
    List<String> updatePhoto(Object[] argument);//修改照片信息
    List<String> updateuser(Object[] argument);
    //更新用户资料，注意为数据null会自动填上原来的，要改成没有输入空串！

    List<String> deleteuser(Object[] argument);//删除用户，会顺便删除学生的一切关联的表以及他的照片
    UserinfoEntity finduser(Object[] argument);//找用户
}
