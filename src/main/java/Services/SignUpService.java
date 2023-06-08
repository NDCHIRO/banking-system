package Services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ORMs.Client;
import ServicesUtility.ClientsSearch;
import ServicesUtility.SessionService;

public class SignUpService {
	
	public SignUpService() {
		// TODO Auto-generated constructor stub
	}
	
	public static void saveClientData(Client client) throws BankSystemException
	{
		checkUserName(client.getName());
		checkValidPassword(client.getPassword());
		checkValidMail(client.getMail());
		Session session = SessionService.startSession();
		if(ClientsSearch.searchForClient(session,client.getName(),client.getPassword()) == true)
		{
			SessionService.endSession(session);
			throw new BankSystemException("username already exists please type another one");
		}
		session.save(client);
		SessionService.endSession(session);
	}

	public static void checkUserName(String username) throws BankSystemException
	{
		if(username.length()<5 || username.length()>10)
			throw new BankSystemException("username must be in range of 5 to 10");
	}
	public static void checkValidPassword(String password)	throws BankSystemException
	{
		String regex = "(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*_]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		if(!matcher.matches())
		   throw new BankSystemException("password is not valid");
	}
	
	public static void checkValidMail(String mail) throws BankSystemException
	{
		String regex = "^[A-Za-z0-9+_.-]+@+[A-Za-z0-9+_.-]+(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		if(!matcher.matches())
			throw new BankSystemException("Mail is not valid");
	}
	
	public static boolean checkClientExists(Session session, Client givenClient)
	{
		String hql = "FROM Client";
		Query<Client> query = session.createQuery(hql,Client.class);
		List<Client> clients = query.getResultList();
		for (Client client : clients) 
		    if((client.getName().equals(givenClient.getName())))
		    	return true;
		return false;
	}
	
	
	
}