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
import ServicesUtility.TablesSearch;
import ServicesUtility.SessionService;

public class SignInService {
	//returns the next page name to the bean
	public static String checkSignInData(String username, String password) throws Exception
	{
	    Session session = SessionService.startSession();
	    if(!TablesSearch.searchForClient(session,username,password))
		{
	    	SessionService.endSession(session);
	    	throw new BankSystemException("please enter the correct username and password");
		}
	    System.out.println("found");
	    Client client = new Client();
	    client.setName(username);
	    client.setPassword(password);
	    client = TablesSearch.searchForClient(session,client);
	    /*ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    Map<String, Object> sessionMap = externalContext.getSessionMap();
	    sessionMap.put("client", client);*/
	    ClientLogin.setClient("client",client);
	    SessionService.endSession(session);
	    if(client.getRole().equals("client"))
	    	return "account";
	    else
	    	return "transaction";
	}
	
	
	
	
	
	
	
	
}
