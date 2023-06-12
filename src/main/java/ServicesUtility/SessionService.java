package ServicesUtility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Services.BankSystemException;
import Services.SessionFactorySingletonPattern;

public class SessionService {
	
	public static Session startSession()  throws Exception
	{
		try {
			SessionFactory sessionFactory = SessionFactorySingletonPattern.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			return session;
		}
		catch(Exception e)
		{
			throw new BankSystemException();
		}
	}
	public static void endSession(Session session) throws Exception	
	{
		try {
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception e)
		{
			throw new BankSystemException();
		}
	}
}
