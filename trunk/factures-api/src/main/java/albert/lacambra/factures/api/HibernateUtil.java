package albert.lacambra.factures.api;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static ServiceRegistry serviceRegistry;

	private static SessionFactory buildSessionFactory() {
		try {
//			@SuppressWarnings("unused")
//			Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

			// Create the SessionFactory from hibernate.cfg.xml
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
		    
		    return configuration.buildSessionFactory(serviceRegistry);
//			return new Configuration().configure().buildSessionFactory();
		}
		catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
