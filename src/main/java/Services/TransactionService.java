package Services;

import org.hibernate.Session;

import ORMs.Account;
import ORMs.Transaction;
import ServicesUtility.BankSystemException;
import ServicesUtility.SessionService;
import ServicesUtility.TablesSearch;

public class TransactionService {
	public static void saveChangesToTransaction(String status, Transaction transaction) throws BankSystemException
	{
		Session session;
		session = SessionService.startSession();
		if(transaction==null)
		{
			SessionService.endSession(session);
			throw new BankSystemException("can't edit this transaction");
		}
		transaction.setDescription(status);
		session.update(transaction);
		if(status.equals("approved"))
			approveTransaction(session,transaction);
		else if(status.equals("rejected"))
			rejectTransaction(session,transaction);
		SessionService.endSession(session);
	}
	
	public static void approveTransaction(Session session,Transaction transaction)
	{
		Account fromAccount = transaction.getFromAccount();
		Account toAccount = transaction.getToAccount();
		fromAccount.setAmount(fromAccount.getAmount()-transaction.getAmountOfTransferedMoney());
		toAccount.setAmount(toAccount.getAmount()+transaction.getAmountOfTransferedMoney());
		
		session.update(fromAccount);
		session.update(toAccount);
	}
	
	public static void rejectTransaction(Session session,Transaction transaction)
	{
		
	}
}
