package Services;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import BeansUtility.LanguagesInfo;
import BeansUtility.MessagesNotification;
import ORMs.Account;
import ORMs.BankEmployee;
import ORMs.Client;
import ServicesUtility.ClientServiceUtility;
import ServicesUtility.Languages;
import ServicesUtility.BankSystemException;
import ServicesUtility.SessionService;

public class SignUpService {
	ResourceBundle bundle = ResourceBundle.getBundle("languages.message");
	 
	public SignUpService() {
		// TODO Auto-generated constructor stub
	}

	public static void saveClientData(Client client) throws BankSystemException
	{ 
		try 
		{
			checkUserName(client);
			checkValidPassword(client);
			checkValidMail(client);
			Session session;
			session = SessionService.startSession();
			if(ClientServiceUtility.searchForClient(session,client.getName(),client.getPassword()) == true)
			{
				SessionService.endSession(session);
				throw new BankSystemException(Languages.createBundle(client).getString("error_userNameUsed"));
			}
			Account account = createAccount(client);
			session.save(client);
			session.save(account);
			SessionService.endSession(session);
		}
		catch(BankSystemException e) {
			throw new BankSystemException(e.getMessage());
		}
		catch(Exception e ) {
			throw new BankSystemException();
		}
	}
		
	public static void checkUserName(Client client) throws BankSystemException {
		//languages is the package name
	    if (client.getName().length() < 5 || client.getName().length() > 10) {
	        throw new BankSystemException(Languages.createBundle(client).getString("error_userNameLength"));
	    }
	}
	
	public static void checkValidPassword(Client client)	throws BankSystemException
	{
		String regex = "(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*_]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(client.getPassword());
		if(!matcher.matches())
		{
			throw new BankSystemException(Languages.createBundle(client).getString("error_passwordNotValid"));
		}
	}
	
	public static void checkValidMail(Client client) throws BankSystemException
	{
		String regex = "^[A-Za-z0-9+_.-]+@+[A-Za-z0-9+_.-]+(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(client.getMail());
		if(!matcher.matches())
		{
			throw new BankSystemException(Languages.createBundle(client).getString("error_mailNotValid"));		
		}
	}

	public static Account createAccount(Client client)
	{
		Account account = new Account();
		account.setAmount(0);
		account.setClient(client);
		return account;
	}
	
	
}
