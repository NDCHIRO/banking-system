package ServicesUtility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactorySingletonPattern {
	private static SessionFactory sessionFactory=null;
	
	static {
		if(sessionFactory == null)
			sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	// is this part a thread safe???
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private SessionFactorySingletonPattern() {
		// TODO Auto-generated constructor stub
	}
	
	
	// searched and found this as a thread safe 
	/*
	 public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (SessionFactorySingletonPattern.class) {
                if (sessionFactory == null) {
                    sessionFactory = new Configuration().configure().buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }
	*/
}
