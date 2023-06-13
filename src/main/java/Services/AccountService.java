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
import ServicesUtility.BankSystemException;
import ServicesUtility.SessionService;

public class AccountService {
	
	
	//module dialiag for transaction

	public static void editClientDataAndAccount(String address,String mail,String mobileNumber, int amountOfMoney)  throws BankSystemException
	{
		Session session = SessionService.startSession();
		Client client = session.get(Client.class,ClientLogin.getClient().getId());
		if(client == null)
			throw new BankSystemException("client data not found");
		client.setAddress(address);
		client.setMail(mail);
		client.setMobile(mobileNumber);
		Account account = session.get(Account.class,getAccount(session,client.getId() ).getId());
		account.setAmount(amountOfMoney);
		session.update(client);
		session.update(account);
		SessionService.endSession(session);		
	}
	
	public static Account getAccount(Session session, int clientId) throws BankSystemException
	{
		Account account =TablesSearch.searchForAccount(session, clientId);
		if(account==null)
			throw new BankSystemException("account not found");
		return account;
	}
	
	
	public static List<Transaction> getAllTransactions() throws BankSystemException
	{
		List<Transaction> transactions;
		Session session = SessionService.startSession();
		 transactions=TablesSearch.getAllTransaction(session);
		SessionService.endSession(session);
		return transactions;   
	}
	
	
	public static void transferMoney(int amountOfMoney,String transferedUsername) throws BankSystemException
	{
		Session session;
		session = SessionService.startSession();
		
		Client fromClient = session.get(Client.class,ClientLogin.getClient().getId());
		Client toClient=session.get(Client.class,TablesSearch.searchForClient(session,transferedUsername).getId());
		if(fromClient==null || toClient==null)
		{
			SessionService.endSession(session);
			throw new BankSystemException("the client does not exist");
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
		
		Transaction transaction = createTransaction(fromAccount,toAccount,fromClient);
		
		session.update(fromAccount);
		session.update(toAccount);
		session.save(transaction);
		
		SessionService.endSession(session);
		System.out.println("money sent");
	}
	
	
	public static Transaction createTransaction(Account fromAccount,Account toAccount,Client fromClient)
	{
		Transaction transaction = new Transaction();
		transaction.setFromAccount(fromAccount);
		transaction.setToAccount(toAccount);
		transaction.setClient(fromClient);
		transaction.setDescription("pending");
		return transaction;
	}
	
	
}