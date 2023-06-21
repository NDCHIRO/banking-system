package BeansUtility;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ORMs.Client;
import ServicesUtility.BankSystemException;

public class ClientLogOut {
	public static void clearClientData() throws BankSystemException
	{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		Client client =  (Client) sessionMap.get("client");
		sessionMap.clear();
	}
	
	public static  void logOutCurrentClient()
	{
		try {
			ClientLogOut.clearClientData();
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		    externalContext.redirect(externalContext.getRequestContextPath() + "/signIn.xhtml");
		} catch (BankSystemException e) {
			// TODO Auto-generated catch block
			MessagesNotification.showErrorMessage("logout failed",e.getMessage());
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			MessagesNotification.showErrorMessage("logout failed","try again");
		}
	}
}
