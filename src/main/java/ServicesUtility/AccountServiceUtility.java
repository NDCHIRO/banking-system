package ServicesUtility;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ORMs.Account;

public class AccountServiceUtility {
	public static Account searchForAccount(Session session,int clientId) throws BankSystemException
	{
		//Session session = SessionService.startSession();
		String hql = "FROM Account";
		Query<Account> query = session.createQuery(hql,Account.class);
		List<Account> accounts = query.getResultList();
		for (Account account : accounts) 
		    if((account.getClient().getId()==(clientId)))
		    {
		    	return account;
		    }
		return null;
	}
	
	public static Account searchForAccount(Session session,String username) throws BankSystemException
	{
		//Session session = SessionService.startSession();
		String hql = "FROM Account";
		Query<Account> query = session.createQuery(hql,Account.class);
		List<Account> accounts = query.getResultList();
		for (Account account : accounts) 
		    if((account.getClient().getName().equals(username)))
		    {
		    	return account;
		    }
		return null;
	}
}
