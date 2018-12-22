package icpc.njust.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public void initTable(){
        //加载配置，初始化
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
    }

    public static void main( String[] args )
    {
        App app=new App();
        app.initTable();

    }
}
