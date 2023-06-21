package ServicesUtility;

import java.util.Locale;
import java.util.ResourceBundle;

import BeansUtility.ClientLogin;
import ORMs.Client;

public class Languages {
	public static ResourceBundle createBundle() throws BankSystemException
	{
		ResourceBundle bundle;
		if(ClientLogin.getClient()==null || ClientLogin.getClient().getSelectedLanguage().equals("en"))
			bundle = ResourceBundle.getBundle("languages.message",Locale.ENGLISH);
		else
			bundle = ResourceBundle.getBundle("languages.message",new Locale("ar","EG"));
		return bundle;
	}
	
	public static ResourceBundle createBundle(Client client) throws BankSystemException
	{
		ResourceBundle bundle;
		if(client.getSelectedLanguage().equals("en"))
			bundle = ResourceBundle.getBundle("languages.message",Locale.ENGLISH);
		else
			bundle = ResourceBundle.getBundle("languages.message",new Locale("ar","EG"));
		return bundle;
	}
}
