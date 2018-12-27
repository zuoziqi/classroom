package icpc.njust.test.Utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtils {
    private static SessionFactory factory;
    private static StandardServiceRegistry registry;
    static{
        registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
    public static Session openSession(){
        return factory.openSession();
    }
}
