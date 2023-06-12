package Services;

import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;

import BeansUtility.ClientLogin;
import BeansUtility.MessagesNotification;
import ORMs.Account;
import ORMs.Client;
import ORMs.Transaction;
import ServicesUtility.TablesSearch;
import ServicesUtility.SessionService;

public class AccountService {
	
	
	//module dialiag for transaction

	public static void editClientDataAndAccount(String address,String mail,String mobileNumber, int amountOfMoney) throws Exception
	{
		try 
		{
			Session session = SessionService.startSession();
			Client client = session.get(Client.class,ClientLogin.getClient().getId());
			client.setAddress(address);
			client.setMail(mail);
			client.setMobile(mobileNumber);
			Account account = session.get(Account.class,getAccount(session,client.getId() ).getId());
			account.setAmount(amountOfMoney);
			session.update(client);
			session.update(account);
			SessionService.endSession(session);
			//put it in the bean
			MessagesNotification.showDoneMessage("data edited successfully", "Done");
		}
		catch(Exception e)
		{
			MessagesNotification.showErrorMessage("edit failed","please try to edit data later");
			throw new BankSystemException();
		}
	}
	
	public static Account getAccount(Session session, int clientId) throws Exception
	{
		Account account =TablesSearch.searchForAccount(session, clientId);
		if(account==null)
		{
			//put it in the bean

			MessagesNotification.showErrorMessage("edit failed","please try to edit data later");
			throw new BankSystemException();
		}
		return account;
	}
	
	/*public static Client getClient()	
	{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		Client client =  (Client) sessionMap.get("client");
		return client;
	}*/
	
	public static List<Transaction> getAllTransactions() throws Exception
	{
		List<Transaction> transactions;
		try {
			Session session = SessionService.startSession();
			 transactions=TablesSearch.getAllTransaction(session);
			SessionService.endSession(session);
		}
		catch(Exception e)
		{
			throw new BankSystemException();
		}
		return transactions;
		    
	}
	
	
	public static void transferMoney(int amountOfMoney,String transferedUsername) throws Exception
	{
		Session session;
		session = SessionService.startSession();
		
		Client fromClient = session.get(Client.class,ClientLogin.getClient().getId());
		Client toClient=session.get(Client.class,TablesSearch.searchForClient(session,transferedUsername).getId());
		if(fromClient==null || toClient==null)
		{
			SessionService.endSession(session);
			throw new BankSystemException("the given name does not exist");
		}
		Account fromAccount = session.get(Account.class,getAccount(session,fromClient.getId() ).getId() );
		Account toAccount = session.get(Account.class,getAccount(session, toClient.getId() ).getId() );
		if(fromAccount==null || toAccount==null)
		{
			SessionService.endSession(session);
			throw new BankSystemException("the given account does not exist");
		}
		
		if(fromAccount.getAmount() < amountOfMoney)	
		{
			SessionService.endSession(session);
			throw new BankSystemException("the amount of Money is more than you");		
		}
		fromAccount.setAmount(fromAccount.getAmount()-amountOfMoney);
		toAccount.setAmount(toAccount.getAmount()+amountOfMoney);
		Transaction transaction = new Transaction();
		transaction.setFromAccount(fromAccount);
		transaction.setToAccount(toAccount);
		transaction.setClient(fromClient);
		
		session.update(fromAccount);
		session.update(toAccount);
		session.save(transaction);
		//session.update(clientMoneyToBeSent);
		SessionService.endSession(session);
		System.out.println("money sent");
		MessagesNotification.showDoneMessage("Done", "Money sent to "+toClient.getName());
	}
	
	
}