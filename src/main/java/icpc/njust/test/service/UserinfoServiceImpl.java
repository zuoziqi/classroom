package icpc.njust.test.service;

import icpc.njust.test.repository.UserinfoDao;
import icpc.njust.test.table.UserinfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UderinfoService")
public class UserinfoServiceImpl implements UserinfoService {
    private final UserinfoDao userinfoDao;
    @Autowired
    public UserinfoServiceImpl(UserinfoDao userinfoDao) {
        this.userinfoDao = userinfoDao;
    }

    @Override
    public void adduser(String id, String name, String identity, String phone, String email, String school, String academy) {

    }

    @Override
    public void updateuser(String id, String name, String identity, String phone, String email, String school, String academy) {

    }

    @Override
    public void deleteuser(String id) {

    }

    @Override
    public UserinfoEntity finduser(String id) {
        return null;
    }
}
