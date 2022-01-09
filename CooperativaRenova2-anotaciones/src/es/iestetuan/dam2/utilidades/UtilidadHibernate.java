package es.iestetuan.dam2.utilidades;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class UtilidadHibernate {
	private static SessionFactory sessionFactory=null;

    public static Session getSession() {
    	if (sessionFactory==null) {
    		crearFactoriaSesiones();
    	}
        return sessionFactory.openSession();
    }

    public static void shutdown() {
          sessionFactory.close();
    }

    private static void crearFactoriaSesiones() {
    	// A SessionFactory is set up once for an application!
    	StandardServiceRegistry registry = null;
   	 	try {
   	 		registry = new StandardServiceRegistryBuilder()
   	 						.configure()
   	 						.build();
    		sessionFactory = new MetadataSources( registry ).
    					buildMetadata().
    					buildSessionFactory();
    	}
    	catch (Exception e) {
    		// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
    		// so destroy it manually.
    		e.printStackTrace();
    		StandardServiceRegistryBuilder.destroy( registry );
    	}
    }
}
