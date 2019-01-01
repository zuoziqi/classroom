package icpc.njust.test.repository;

import icpc.njust.test.Utils.HibernateUtils;
import icpc.njust.test.table.ClassTeacherEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
@Repository("ClassTeacherDao")
public class ClassTeacherDaoImpl implements ClassTeacherDao {

    @Override
    public void create(String classid, String id, String classname) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            //transaction.begin();
            ClassTeacherEntity classTeacherEntity=new ClassTeacherEntity();
            classTeacherEntity.setClassname(classname);
            classTeacherEntity.setId(id);
            classTeacherEntity.setClassid(classid);
            classTeacherEntity.setClasstimes("0");
            session.save(classTeacherEntity);
            transaction.commit();
            System.out.println("successful saved.");
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public void delete(String classid) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            //transaction.begin();
            String hql="from ClassTeacherEntity c where c.classid=:classid";
            ClassTeacherEntity classTeacherEntity= (ClassTeacherEntity) session.createQuery(hql).setParameter("classid",classid).uniqueResult();
            session.delete(classTeacherEntity);
            transaction.commit();
            System.out.println("successful delete.");
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<ClassTeacherEntity> showall() {
        Session session= HibernateUtils.openSession();
        try{
            List<ClassTeacherEntity> classTeacherEntities=(List<ClassTeacherEntity>)session.createQuery("from ClassTeacherEntity").list();
            return classTeacherEntities;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public ClassTeacherEntity find(String classid) {
        ClassTeacherEntity classTeacherEntity=new ClassTeacherEntity();
        Session session= HibernateUtils.openSession();
        try{
            classTeacherEntity= (ClassTeacherEntity) session.createQuery("from ClassTeacherEntity c where c.classid=:classid").setParameter("classid",classid).uniqueResult();
            return classTeacherEntity;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<ClassTeacherEntity> findByTeacher(String teacherid) {
        Session session= HibernateUtils.openSession();
        try{
            List<ClassTeacherEntity> classTeacherEntities= (List<ClassTeacherEntity>) session.createQuery("from ClassTeacherEntity c where c.id=:id").setParameter("id",teacherid).list();
            return classTeacherEntities;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void update(String classid, String id, String classname, String classtimes) {
        Query classTeacherEntity=null;
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            //transaction.begin();
            String hql="update ClassTeacherEntity c set c.classid=:classid,c.id=:id,c.classname=:classname,c.classtimes=:classtimes";
            classTeacherEntity=session.createQuery(hql).setParameter("classid",classid).setParameter("id",id).setParameter("classname",classname).setParameter("classtimes",classtimes);
            ClassTeacherEntity classTeacherEntity1=new ClassTeacherEntity();
            classTeacherEntity1.setClassid(classid);
            classTeacherEntity1.setId(id);
            classTeacherEntity1.setClassname(classname);
            classTeacherEntity1.setClasstimes(classtimes);
            classTeacherEntity.setProperties(classTeacherEntity1);
            classTeacherEntity.executeUpdate();
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateTeacher(String classid, String teacherid) {
        Query classTeacherEntity=null;
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            //transaction.begin();
            String hql="update ClassTeacherEntity c set c.id=:id where c.classid=:classid";
            classTeacherEntity=session.createQuery(hql).setParameter("id",teacherid).setParameter("classid",classid);
            classTeacherEntity.executeUpdate();
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateClassname(String classid, String classname) {
        Query classTeacherEntity=null;
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            //transaction.begin();
            String hql="update ClassTeacherEntity c set c.classname=:classname where c.classid=:classid";
            classTeacherEntity=session.createQuery(hql).setParameter("classname",classname).setParameter("classid",classid);
            classTeacherEntity.executeUpdate();
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public void addClasstime(String classid) {
        ClassTeacherEntity classTeacherEntity;
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            //transaction.begin();
            classTeacherEntity=session.find(ClassTeacherEntity.class,classid);
            classTeacherEntity.setClasstimes(String.valueOf(Integer.valueOf(classTeacherEntity.getClasstimes())+1));
            session.save(classTeacherEntity);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
