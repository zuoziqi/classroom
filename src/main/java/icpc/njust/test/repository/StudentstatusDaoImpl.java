package icpc.njust.test.repository;

import icpc.njust.test.Utils.HibernateUtils;
import icpc.njust.test.table.StudentstatusEntity;
import icpc.njust.test.table.WarninginfoEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by DELL on 2018/12/23.
 */
public class StudentstatusDaoImpl implements StudentstatusDao{
    @Override
    public void create(String studentid, String classid, String classcnt, String attend, String warningnumber) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            transaction.begin();
            StudentstatusEntity studentstatusEntity=new StudentstatusEntity();
            studentstatusEntity.setStudentid(studentid);
            studentstatusEntity.setClassid(classid);
            studentstatusEntity.setClasscnt(classcnt);
            studentstatusEntity.setAttend(attend);
            studentstatusEntity.setWarningnumber(warningnumber);
            session.save(studentstatusEntity);
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
    public void delete(String recordid) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            transaction.begin();
            String hql="from StudentstatusEntity s where s.recordid=:recordid";
            StudentstatusEntity studentstatusEntity= (StudentstatusEntity) session.createQuery(hql).setParameter("recordid",recordid).uniqueResult();
            session.delete(studentstatusEntity);
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
    public void clear(String classid, String classcnt, String studentid) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            String hql="from StudentstatusEntity s where s.classid=:classid and s.classcnt=:classcnt and s.studentid=:studentid";
            StudentstatusEntity studentstatusEntity= (StudentstatusEntity) session.createQuery(hql)
                    .setParameter("classid", classid)
                    .setParameter("classcnt", classcnt)
                    .setParameter("studentid", studentid)
                    .uniqueResult();
            session.delete(studentstatusEntity);
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
    public List<StudentstatusEntity> showall() {
        Session session= HibernateUtils.openSession();
        try{
            String hql="from StudentstatusEntity";
            List<StudentstatusEntity> studentstatusEntities= (List<StudentstatusEntity>) session.createQuery(hql).list();
            return studentstatusEntities;
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public StudentstatusEntity find(String id) {
        Session session= HibernateUtils.openSession();
        try{
            StudentstatusEntity studentstatusEntity= (StudentstatusEntity) session.createQuery("from StudentstatusEntity u where u.id=:id").setParameter("id",id).uniqueResult();
            return studentstatusEntity;
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<StudentstatusEntity> findByClassStudent(String classid, String studentid) {
        Session session= HibernateUtils.openSession();
        try{
            List<StudentstatusEntity> studentstatusEntities=(List<StudentstatusEntity>)session.createQuery("from StudentstatusEntity s where s.classid=:classid and s.studentid=:studentid")
                    .setParameter("classid",classid)
                    .setParameter("studentid",studentid)
                    .list();
            return  studentstatusEntities;
        }finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public void update(String recordid,String studentid, String classid, String classcnt, String attend, String warningnumber) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            StudentstatusEntity studentstatusEntity=new StudentstatusEntity();
            String hql="update StudentstatusEntity s set s.studentid=:studentid,s.classid=:classid,s.attend=:attend,s.warningnumber=:warningnumber where s.recordid=:recordid";
            studentstatusEntity= (StudentstatusEntity) session.createQuery(hql)
            .setParameter("studentid",studentid)
            .setParameter("classid",classid)
            .setParameter("attend",attend)
            .setParameter("warningnumber",warningnumber)
            .uniqueResult();
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
}
