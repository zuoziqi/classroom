package icpc.njust.test.repository;

import icpc.njust.test.table.UserinfoEntity;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public class UserinfoDaoImpl implements UserinfoDao {

    @Override
    public void create(String id, String name, String phone, String email, String school, String academy, String identity) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<UserinfoEntity> showall() {
        return null;
    }

    @Override
    public UserinfoEntity find(String id) {
        return null;
    }

    @Override
    public void update(String id, String name, String phone, String email, String school, String academy, String identity) {

    }

    @Override
    public void updateInfo(String phone, String email, String school, String academy) {

    }
}
