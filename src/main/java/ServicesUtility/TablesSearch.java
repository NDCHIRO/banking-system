package ServicesUtility;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ORMs.Account;
import ORMs.Client;
import ORMs.Transaction;

public class TablesSearch {
	
	public static boolean searchForClient(Session session,String username,String password)
	{
		String hql = "FROM Client";
		Query<Client> query = session.createQuery(hql,Client.class);
		List<Client> clients = query.getResultList();
		for (Client client : clients) 
		    if((client.getName().equals(username))&&(client.getPassword().equals(password)))
		    	return true;
		return false;
	}
	
	public static Client searchForClient(Session session,Client givenClient) throws BankSystemException
	{
		String hql = "FROM Client";
		Query<Client> query = session.createQuery(hql,Client.class);
		List<Client> clients = query.getResultList();
		for (Client client : clients) 
		    if((client.getName().equals(givenClient.getName()))
		    		&&(client.getPassword().equals(givenClient.getPassword())))
		    	return client;
		return null;
	}
	
	public static Client searchForClient(Session session,String username)  throws BankSystemException
	{
		String hql = "FROM Client";
		Query<Client> query = session.createQuery(hql,Client.class);
		List<Client> clients = query.getResultList();
		for (Client client : clients) 
		    if((client.getName().equals(username)))
		    	return client;
		return null;
	}
	
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
	
	public static List<Transaction> getAllTransaction(Session session)
	{
		//List<Transaction> transactions = new ArrayList<Transaction>();
		String hql = "FROM Transaction";
		Query<Transaction> query = session.createQuery(hql,Transaction.class);
		List<Transaction> transactions = query.getResultList();
		return transactions;    
	}
}
