package icpc.njust.test.repository;

import icpc.njust.test.Utils.HibernateUtils;
import icpc.njust.test.table.PhotostorageEntity;
import icpc.njust.test.table.UserinfoEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
@Repository("PhotostorageDao")
public class PhotostorageDaoImpl implements PhotostorageDao {

    @Override
    public void create(String id, String image_base64, String facetoken) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            PhotostorageEntity photostorageEntity=new PhotostorageEntity();
            photostorageEntity.setId(id);
            photostorageEntity.setImageBase64(image_base64);
            photostorageEntity.setFacetoken(facetoken);
            session.save(photostorageEntity);
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
            //

            String hql="from PhotostorageEntity p where p.id=:id";
            PhotostorageEntity photostorageEntity= (PhotostorageEntity) session.createQuery(hql).setParameter("id",id);
            session.delete(photostorageEntity);
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
    public List<PhotostorageEntity> showall() {
        Session session= HibernateUtils.openSession();
        try{
            List<PhotostorageEntity> photostorageEntities=(List<PhotostorageEntity>)session.createQuery("from PhotostorageEntity");
            return photostorageEntities;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public PhotostorageEntity find(String id) {
        PhotostorageEntity photostorageEntity=new PhotostorageEntity();
        Session session= HibernateUtils.openSession();
        try{
            photostorageEntity= (PhotostorageEntity) session.createQuery("from PhotostorageEntity p where p.id=:id").setParameter("id",id);
            return photostorageEntity;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void update(String id, String image_base64, String facetoken) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        PhotostorageEntity photostorageEntity=new PhotostorageEntity();
        try{
            photostorageEntity=session.find(PhotostorageEntity.class,id);
            photostorageEntity.setImageBase64(image_base64);
            photostorageEntity.setFacetoken(facetoken);
            session.save(photostorageEntity);
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
