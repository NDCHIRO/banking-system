package BeansUtility;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ORMs.Client;
import ServicesUtility.BankSystemException;

public class ClientLogin {
	public static Client getClient() throws BankSystemException
	{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		Client client =  (Client) sessionMap.get("client");
		return client;
	}
	
	public static void setClient(String s,Client client) throws BankSystemException
	{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    Map<String, Object> sessionMap = externalContext.getSessionMap();
	    sessionMap.put(s, client);
	}
}
