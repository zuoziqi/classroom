package icpc.njust.test.repository;


import icpc.njust.test.Utils.HibernateUtils;
import icpc.njust.test.table.ClassStudentEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public class ClassStudentDaoImpl implements ClassStudentDao {

    @Override
    public void create(String chooseid, String id, String classid) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            ClassStudentEntity classStudentEntity=new ClassStudentEntity();
            classStudentEntity.setChooseid(chooseid);
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
            ClassStudentEntity classStudentEntity=new ClassStudentEntity();
            classStudentEntity.setChooseid(chooseid);
            session.delete(classStudentEntity);
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
    public void delete(String id, String classid) {

    }

    @Override
    public List<ClassStudentEntity> showall() {
        return null;
    }


    @Override
    public ClassStudentEntity find(String chooseid) {

        return null;
    }

    @Override
    public List<ClassStudentEntity> findByClass(String classid) {
        return null;
    }

    @Override
    public List<ClassStudentEntity> findByStudent(String id) {
        return null;
    }

}
