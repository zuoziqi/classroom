package icpc.njust.test.service;

import icpc.njust.test.table.ClassTeacherEntity;

import java.util.List;

public interface ClassService {
    void addclass(String classid, String classname, String teacherid);
    void changeteacher(String classid, String teacherid);
    void deleteclass(String classid);
    void clear();
    void addstudent(String classid, String studentid);
    ClassTeacherEntity findclass(String classid);
    List<ClassTeacherEntity> findByTeacher(String teacherid);
    List<ClassTeacherEntity> findByStudent(String student);
}
