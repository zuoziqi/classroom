package icpc.njust.test.service;

import icpc.njust.test.table.ClassTeacherEntity;

import java.util.List;

public interface ClassService {
    List<String> addClass(Object[] argument);
    List<String> changeteacher(Object[] argument);
    List<String> deleteclass(Object[] argument);
    List<String> clear();
    List<String> addstudent(Object[] argument);
    ClassTeacherEntity findclass(Object[] argument);
    List<ClassTeacherEntity> findByTeacher(Object[] argument);
    List<ClassTeacherEntity> findByStudent(Object[] argument);
}
