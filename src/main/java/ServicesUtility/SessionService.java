package ServicesUtility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionService {
	
	public static Session startSession()  throws BankSystemException
	{
		try {
			SessionFactory sessionFactory = SessionFactorySingletonPattern.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			return session;
		}
		/*Unreachable catch block for BankSystemException. This exception is never thrown from the try statement body
		 * catch(BankSystemException e)
		{
			throw new BankSystemException();
		}*/
		catch(Exception e)
		{
			throw new BankSystemException();
		}
	}
	
	
	public static void endSession(Session session) throws BankSystemException	
	{
		try {
			session.getTransaction().commit();
			session.close();
		}
		/*Unreachable catch block for BankSystemException. This exception is never thrown from the try statement body
		 * catch(BankSystemException e)
		{
			throw new BankSystemException();
		}*/
		catch(Exception e)
		{
			throw new BankSystemException();
		}
	}
}
