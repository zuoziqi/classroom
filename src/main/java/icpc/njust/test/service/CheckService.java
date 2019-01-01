package icpc.njust.test.service;

import icpc.njust.test.table.StudentstatusEntity;
import icpc.njust.test.table.WarninginfoEntity;

import java.io.IOException;
import java.util.List;

public interface CheckService {
    void signin(String classid, String image_base64) throws IOException;
    void checkstatus(String classid, String image_base64) throws IOException;
    List findstatusByOneclass(String classid, String classcnt);
    List<StudentstatusEntity> findstatusByStudent(String classid, String studentid);
    List<WarninginfoEntity> findwarningByOneclass(String classid, String classcnt);
    List<WarninginfoEntity> findwarningByStudent(String classid, String studentid);
}
