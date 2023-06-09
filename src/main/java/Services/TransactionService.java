package Services;

import org.hibernate.Session;

import ORMs.Account;
import ORMs.Transaction;
import ServicesUtility.BankSystemException;
import ServicesUtility.SessionService;
import ServicesUtility.ClientServiceUtility;

public class TransactionService {
	public static void saveChangesToTransaction(String status, Transaction transaction) throws BankSystemException
	{//move all validators up before the session
		try 
		{
			if(transaction==null)
				throw new BankSystemException("can't edit this transaction");
			if(transaction.getDescription().equals("pending") && status.equals("pending"))
			{	
				System.out.println("same state which is: "+transaction.getDescription());
				throw new BankSystemException("same state which is: "+transaction.getDescription());
			}
			if(!(transaction.getDescription().equals("pending")))
			{	
				System.out.println("this transaction has already been "+transaction.getDescription());
				throw new BankSystemException("this transaction has already been "+transaction.getDescription());
			}
			if(transaction.getFromAccount().getAmount()<transaction.getAmountOfTransferedMoney())
			{
				rejectTransaction(transaction);
				status="rejected";
			}
			Session session;
			session = SessionService.startSession();
			transaction.setDescription(status);
			session.update(transaction);
			if(status.equals("approved"))
				approveTransaction(session,transaction);
			else if(status.equals("rejected"))
				rejectTransaction(transaction);
			SessionService.endSession(session);
			
		}
		catch(BankSystemException e) {
			throw new BankSystemException(e.getMessage());
		}
		catch(Exception e ) {
			throw new BankSystemException();
		}
	}
	
	public static void approveTransaction(Session session,Transaction transaction) throws BankSystemException
	{
		//check the amount before make the transaction and if there is a problem throw exception
		try
		{
			Account fromAccount = transaction.getFromAccount();
			Account toAccount = transaction.getToAccount();
			fromAccount.setAmount(fromAccount.getAmount()-transaction.getAmountOfTransferedMoney());
			toAccount.setAmount(toAccount.getAmount()+transaction.getAmountOfTransferedMoney());
			
			session.update(fromAccount);
			session.update(toAccount);
		    //session.delete(transaction);
		}
		catch(Exception e ) {
			throw new BankSystemException();
		}

	}
	
	public static void rejectTransaction(Transaction transaction) 
	{
		
	}
}
