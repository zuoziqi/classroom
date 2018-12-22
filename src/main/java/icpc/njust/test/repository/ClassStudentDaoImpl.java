package icpc.njust.test.repository;

import com.mysql.cj.xdevapi.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import icpc.njust.test.table.ClassStudentEntity;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public class ClassStudentDaoImpl implements ClassStudentDao {
    private static SessionFactory sessionFactory;
    static {
        Configuration cfg=new Configuration();
        cfg.configure();
        sessionFactory= (SessionFactory) cfg.buildSessionFactory();
    }
    @Override
    public void addClassStudent(String chooseid, String id, String classid) {
        Session session=sessionFactory.openSession();
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
