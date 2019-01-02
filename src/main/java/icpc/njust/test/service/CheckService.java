package icpc.njust.test.service;

import icpc.njust.test.table.StudentstatusEntity;
import icpc.njust.test.table.WarninginfoEntity;

import java.util.List;

public interface CheckService {
    List<String> signin(Object[] argument) throws Exception;
    List<String> checkstatus(Object[] argument) throws Exception;
    List findstatusByOneclass(Object[] argument);
    List<StudentstatusEntity> findstatusByStudent(Object[] argument);
    List<WarninginfoEntity> findwarningByOneclass(Object[] argument);
    List<WarninginfoEntity> findwarningByStudent(Object[] argument);
}
