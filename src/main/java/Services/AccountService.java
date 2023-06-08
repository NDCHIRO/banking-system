package Services;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ORMs.Client;

public class AccountService {
	
	public static void getCurrentClient()
	{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		Client client =  (Client) sessionMap.get("client");
		System.out.println(client.getName());
	}
}