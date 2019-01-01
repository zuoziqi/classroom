package icpc.njust.test.repository;


import icpc.njust.test.Utils.HibernateUtils;
import icpc.njust.test.table.ClassStudentEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
@Repository("ClassStudentDao")
public class ClassStudentDaoImpl implements ClassStudentDao {

    @Override
    public void create(String id, String classid) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            transaction.begin();
            ClassStudentEntity classStudentEntity=new ClassStudentEntity();
            classStudentEntity.setId(id);
            classStudentEntity.setClassid(classid);
            session.save(classStudentEntity);
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
    public void delete(String chooseid) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            transaction.begin();
            ClassStudentEntity classStudentEntity=new ClassStudentEntity();
            classStudentEntity.setChooseid(chooseid);
            session.delete(classStudentEntity);
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
    public void delete(String id, String classid) {
        ClassStudentEntity classStudentEntity=null;
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            transaction.begin();
            String hql="from ClassStudentEntity c where c.id=:id and c.classid=:classid";
            classStudentEntity=(ClassStudentEntity)session.createQuery(hql).setParameter("id",id).setParameter("classid",classid);
            session.delete(classStudentEntity);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void clearByStudent(String id) {
        ClassStudentEntity classStudentEntity=null;
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            transaction.begin();
            String hql="from ClassStudentEntity c where c.id=:id";
            classStudentEntity=(ClassStudentEntity)session.createQuery(hql).setParameter("id",id);
            session.delete(classStudentEntity);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void clearByClass(String classid) {
        ClassStudentEntity classStudentEntity=null;
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            transaction.begin();
            String hql="from ClassStudentEntity c where c.classid=:classid";
            classStudentEntity=(ClassStudentEntity)session.createQuery(hql).setParameter("classid",classid);
            session.delete(classStudentEntity);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<ClassStudentEntity> showall() {
        Session session= HibernateUtils.openSession();
        try{
            List<ClassStudentEntity> classStudentEntities=(List<ClassStudentEntity>)session.createQuery("from ClassStudentEntity");
            //for(ClassStudentEntity classStudentEntity:classStudentEntities){}//test
            return classStudentEntities;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    @Override
    public ClassStudentEntity find(String chooseid) {
        ClassStudentEntity classStudentEntity=new ClassStudentEntity();
        Session session= HibernateUtils.openSession();
        try{
            String hql="from ClassStudentEntity as a where a.chooseid=:chooseid";
            classStudentEntity= (ClassStudentEntity) session.createQuery(hql).setParameter("chooseid",chooseid);
            return classStudentEntity;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<ClassStudentEntity> findByClass(String classid) {
        Session session= HibernateUtils.openSession();
        try{
            String hql="from ClassStudentEntity as a where a.classid=:classid";
            List<ClassStudentEntity> classStudentEntities= (List<ClassStudentEntity>) session.createQuery(hql).setParameter("classid",classid);
            return classStudentEntities;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<ClassStudentEntity> findByStudent(String id) {
        Session session= HibernateUtils.openSession();
        try{
            String hql="from ClassStudentEntity as a where a.id=:id";
            List<ClassStudentEntity> classStudentEntities= (List<ClassStudentEntity>) session.createQuery(hql).setParameter("id",id);
            return classStudentEntities;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
