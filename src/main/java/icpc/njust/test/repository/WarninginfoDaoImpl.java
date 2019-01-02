package icpc.njust.test.repository;

import icpc.njust.test.Utils.HibernateUtils;
import icpc.njust.test.table.WarninginfoEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DELL on 2018/12/23.
 */
@Repository("WarninginfoDao")
public class WarninginfoDaoImpl implements WarninginfoDao{

    @Override
    public void create(String studentid, String classid, String classcnt, String warningcontent, String time) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            WarninginfoEntity warninginfoEntity=new WarninginfoEntity();
            warninginfoEntity.setId(studentid);
            warninginfoEntity.setClassid(classid);
            warninginfoEntity.setClasscnt(classcnt);
            warninginfoEntity.setWarningcontent(warningcontent);
            warninginfoEntity.setTime(time);
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
    public void delete(String warningid) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            WarninginfoEntity warninginfoEntity=new WarninginfoEntity();
            String hql="from WarninginfoEntity w where w.warningid=:warningid";
            warninginfoEntity= (WarninginfoEntity) session.createQuery(hql).setParameter("warningid",warningid).uniqueResult();
            session.delete(warninginfoEntity);
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
    public void clear(String classid, String classcnt, String id) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            String hql="from WarninginfoEntity s where s.classid=:classnumber and s.classcnt=:classcnt and s.id=:studentid";
            WarninginfoEntity warninginfoEntity= (WarninginfoEntity) session.createQuery(hql)
                    .setParameter("classnumber", classid)
                    .setParameter("classcnt", classcnt)
                    .setParameter("studentid", id)
                    .uniqueResult();
            session.delete(warninginfoEntity);
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
    public void clearByClass(String classid) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            WarninginfoEntity warninginfoEntity=new WarninginfoEntity();
            String hql="from WarninginfoEntity w where w.classid=:classid";
            warninginfoEntity= (WarninginfoEntity) session.createQuery(hql).setParameter("classid",classid);
            session.delete(warninginfoEntity);
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
    public void clearByStudent(String studentid) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            WarninginfoEntity warninginfoEntity=new WarninginfoEntity();
            String hql="from WarninginfoEntity w where w.id=:id";
            warninginfoEntity= (WarninginfoEntity) session.createQuery(hql).setParameter("id",studentid).uniqueResult();
            session.delete(warninginfoEntity);
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
    public List<WarninginfoEntity> showall() {
        Session session= HibernateUtils.openSession();

        try{
            String hql="from WarninginfoEntity ";
            List<WarninginfoEntity> warninginfoEntities= (List<WarninginfoEntity>) session.createQuery(hql).list();
            return warninginfoEntities;
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public WarninginfoEntity find(String warningid) {
        Session session= HibernateUtils.openSession();
        try{
        WarninginfoEntity warninginfoEntity= (WarninginfoEntity) session.createQuery("from WarninginfoEntity w where w.warningid=:warningid")
                .setParameter("warningid", warningid)
                .uniqueResult();
            return warninginfoEntity;
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<WarninginfoEntity> findByOneClass(String classid, String classcnt) {
        Session session= HibernateUtils.openSession();
        try{
            List<WarninginfoEntity> warninginfoEntities=(List<WarninginfoEntity>)session.createQuery("from WarninginfoEntity w where w.classid=:classid and w.classcnt=:classcnt")
                    .setParameter("classid",classid)
                    .setParameter("classcnt",classcnt)
                    .list();
            return warninginfoEntities;
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<WarninginfoEntity> findByClassStudent(String classid, String studentid) {
        Session session= HibernateUtils.openSession();
        try{
            List<WarninginfoEntity> warninginfoEntities=(List<WarninginfoEntity>)session.createQuery("from WarninginfoEntity w where w.classid=:classid and w.id=:id")
                    .setParameter("classid",classid)
                    .setParameter("id",studentid)
                    .list();
            return warninginfoEntities;
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<WarninginfoEntity> findByOneClassStudent(String classid, String classcnt, String studentid) {
        Session session= HibernateUtils.openSession();
        try{
            List<WarninginfoEntity> warninginfoEntities=(List<WarninginfoEntity>)session.createQuery("from WarninginfoEntity w where w.classid=:classid and w.classcnt=:classcnt and w.id=:id")
                    .setParameter("classid",classid)
                    .setParameter("classcnt",classcnt)
                    .setParameter("id",studentid)
                    .list();
            return warninginfoEntities;
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public void update(String warningid, String studentid, String classid, String classcnt, String warningcontent, String time) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            WarninginfoEntity warninginfoEntity=session.find(WarninginfoEntity.class,warningid);
            warninginfoEntity.setWarningid(warningid);
            warninginfoEntity.setId(studentid);
            warninginfoEntity.setClassid(classid);
            warninginfoEntity.setClasscnt(classcnt);
            warninginfoEntity.setWarningcontent(warningcontent);
            warninginfoEntity.setTime(time);
            session.save(warninginfoEntity);
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }
    }
}
