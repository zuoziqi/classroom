package icpc.njust.test.repository;

import icpc.njust.test.table.PhotostorageEntity;
import icpc.njust.test.table.UserinfoEntity;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public interface PhotostorageDao {
    void create(String id, String image_base64, String facetoken);//增加
    void delete(String id);//删除
    List<PhotostorageEntity> showall();//显示所有
    PhotostorageEntity find(String id);//根据id查询
    void update(String id, String image_base64, String facetoken);//修改
}
