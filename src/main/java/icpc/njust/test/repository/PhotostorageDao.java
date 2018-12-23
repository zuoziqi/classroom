package icpc.njust.test.repository;

import icpc.njust.test.table.UserinfoEntity;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public interface PhotostorageDao {
    void addPhotostorage(String id,String image_base64);//增加
    void deletePhotostorage(String id);//删除
    List<UserinfoEntity> showall();//显示所有
    UserinfoEntity findPhotostorage(String id);//根据id查询
    void changePhotostorage(String id,String image_base64);//修改
}
