package ServicesUtility;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ORMs.Client;

public class ClientsSearch {
	
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
	
	public static Client searchForClient(Session session,Client givenClient)
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
}
