package ServicesUtility;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ORMs.Transaction;

public class TransactionServiceUtility {
	public static List<Transaction> getAllTransaction(Session session)
	{
		//List<Transaction> transactions = new ArrayList<Transaction>();
		String hql = "FROM Transaction";
		Query<Transaction> query = session.createQuery(hql,Transaction.class);
		List<Transaction> transactions = query.getResultList();
		return transactions;    
	}
	
	public static List<Transaction> showAllTransactions() throws BankSystemException
	{
		List<Transaction> transactions;
		Session session = SessionService.startSession();
		 transactions=getAllTransaction(session);
		SessionService.endSession(session);
		return transactions;   
	}
}
