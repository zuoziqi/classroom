package icpc.njust.test.repository;

import icpc.njust.test.table.ClassStudentEntity;
import icpc.njust.test.table.ClassTeacherEntity;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public class ClassTeacherDaoImpl implements ClassTeacherDao {

    @Override
    public void create(String classid, String id, String classname) {

    }

    @Override
    public void delete(String classid) {

    }

    @Override
    public List<ClassTeacherEntity> showall() {
        return null;
    }

    @Override
    public ClassTeacherEntity find(String classid) {
        return null;
    }

    @Override
    public List<ClassTeacherEntity> findByTeacher(String teacherid) {
        return null;
    }

    @Override
    public void update(String classid, String id, String classname, String classtimes) {

    }

    @Override
    public void updateTeacher(String teacherid) {

    }

    @Override
    public void updateClassname(String classname) {

    }

    @Override
    public void addClasstime() {

    }
}
