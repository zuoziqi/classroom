package icpc.njust.test.repository;


import icpc.njust.test.Utils.HibernateUtils;
import icpc.njust.test.table.ClassStudentEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public class ClassStudentDaoImpl implements ClassStudentDao {

    @Override
    public void addClassStudent(String chooseid, String id, String classid) {
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
        }catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }finally {
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public void deleteClsaaStudent(String chooseid) {

    }

    @Override
    public List<ClassStudentEntity> showall() {
        return null;
    }

    @Override
    public ClassStudentEntity findClassStudent(String chooseid) {
        return null;
    }

    @Override
    public void changeClassStudent(String chooseid, String id, String classid) {

    }
}
