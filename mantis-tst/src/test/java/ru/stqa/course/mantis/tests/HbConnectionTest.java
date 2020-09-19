package ru.stqa.course.mantis.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.course.mantis.model.UserData;

import java.util.List;


public class HbConnectionTest extends TestBase{
    private SessionFactory sessionFactory;

    @BeforeClass
    public void setUp() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
       // try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
       /* }
        catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
            throw e;
        }*/
    }

    @Test
    public void testHbConnection() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery( "from UserData where username!='administrator'" ).list();
        for ( UserData user : result ) {
            System.out.println(user.getUsername());
        }

        //app.db().users();
        //System.out.println(i);
        session.getTransaction().commit();
        session.close();
    }


}
