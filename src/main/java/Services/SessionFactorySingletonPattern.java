package Services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactorySingletonPattern {
	private static SessionFactory sessionFactory=null;
	
	static {
		if(sessionFactory == null)
			sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private SessionFactorySingletonPattern() {
		// TODO Auto-generated constructor stub
	}
}
