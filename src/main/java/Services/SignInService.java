package Services;


import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import BeansUtility.ClientLogin;
import ORMs.Client;
import ServicesUtility.ClientServiceUtility;
import ServicesUtility.BankSystemException;
import ServicesUtility.SessionService;

public class SignInService {
	//returns the next page name to the bean
	public static String checkSignInData(String username, String password) throws BankSystemException
	{
		try
		{
		    Session session = SessionService.startSession();
		    Client client = new Client();
		    client.setName(username);
		    client.setPassword(password);
		    client = ClientServiceUtility.searchForClient(session,client);
		    if(client==null)
			{
		    	SessionService.endSession(session);
		    	throw new BankSystemException("please enter the correct username and password");
			}
		    System.out.println("found");
		    ClientLogin.setClient("client",client);
		    SessionService.endSession(session);
		    if(client.getRole().equals("client"))
		    	return "account";
		    else
		    	return "transaction";
		}
		catch(BankSystemException e) {
			throw new BankSystemException(e.getMessage());
		}
		catch(Exception e ) {
			throw new BankSystemException();
		}
	}
	
	
	
}
