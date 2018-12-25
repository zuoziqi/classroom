package icpc.njust.test.repository;

import icpc.njust.test.Utils.HibernateUtils;
import icpc.njust.test.table.UserinfoEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public class UserinfoDaoImpl implements UserinfoDao {

    @Override
    public void create(String id, String name, String phone, String email, String school, String academy, String identity) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            UserinfoEntity userinfoEntity=new UserinfoEntity();
            userinfoEntity.setId(id);
            userinfoEntity.setName(name);
            userinfoEntity.setPhone(phone);
            userinfoEntity.setEmail(email);
            userinfoEntity.setSchool(school);
            userinfoEntity.setAcademy(academy);
            userinfoEntity.setIdentity(identity);
            session.save(userinfoEntity);
            transaction.commit();
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
    public void delete(String id) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            transaction.begin();
            String hql="from UserinfoEntity u where u.id=:id";
            UserinfoEntity userinfoEntity= (UserinfoEntity) session.createQuery(hql).setParameter("id",id).uniqueResult();
            session.delete(userinfoEntity);
            transaction.commit();
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
    public List<UserinfoEntity> showall() {
        Session session= HibernateUtils.openSession();
        try{
            List<UserinfoEntity> userinfoEntities=(List<UserinfoEntity>)session.createQuery("from UserinfoEntity ").list();
            return userinfoEntities;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public UserinfoEntity find(String id) {
        Session session= HibernateUtils.openSession();
        try{
            UserinfoEntity userinfoEntity= (UserinfoEntity) session.createQuery("from UserinfoEntity u where u.id=:id").setParameter("id",id).list();
            return userinfoEntity;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void update(String id, String name, String phone, String email, String school, String academy, String identity) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            transaction.begin();
            UserinfoEntity userinfoEntity=session.find(UserinfoEntity.class,id);
            if(name!=null) userinfoEntity.setName(name);
            if(phone!=null) userinfoEntity.setPhone(phone);
            if(email!=null) userinfoEntity.setEmail(email);
            if(school!=null) userinfoEntity.setSchool(school);
            if(academy!=null) userinfoEntity.setAcademy(academy);
            if(identity!=null) userinfoEntity.setIdentity(identity);
            session.save(userinfoEntity);
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
    public void updateInfo(String id,String phone, String email, String school, String academy) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            transaction.begin();
            UserinfoEntity userinfoEntity=session.find(UserinfoEntity.class,id);
            userinfoEntity.setPhone(phone);
            userinfoEntity.setEmail(email);
            userinfoEntity.setSchool(school);
            userinfoEntity.setAcademy(academy);
            session.save(userinfoEntity);
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
