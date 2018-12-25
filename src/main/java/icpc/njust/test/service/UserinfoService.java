package icpc.njust.test.service;

import icpc.njust.test.table.UserinfoEntity;

import java.io.IOException;

public interface UserinfoService {
    void adduser(String id, String name, String identity, String phone, String email, String school, String academy);
    boolean addPhoto(String id, String image_base64) throws IOException;//增加照片信息，得有这个学生才能加,返回是否成功
    void updatePhoto(String id, String image_base64);//修改照片信息
    void updateuser(String id, String name, String identity, String phone, String email, String school, String academy);
    //更新用户资料，注意为数据null会自动填上原来的，要改成没有输入空串！

    void deleteuser(String id);//删除用户，会顺便删除学生的一切关联的表以及他的照片
    UserinfoEntity finduser(String id);//找用户
}
