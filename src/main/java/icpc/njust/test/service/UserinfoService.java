package icpc.njust.test.service;

import icpc.njust.test.table.UserinfoEntity;

public interface UserinfoService {
    void adduser(String id, String name, String identity, String phone, String email, String school, String academy);
    void updateuser(String id, String name, String identity, String phone, String email, String school, String academy);
    void deleteuser(String id);
    UserinfoEntity finduser(String id);
}
