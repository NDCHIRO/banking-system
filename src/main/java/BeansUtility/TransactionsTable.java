package BeansUtility;

import java.util.List;

import ORMs.Transaction;
import Services.AccountService;
import ServicesUtility.BankSystemException;

public class TransactionsTable {
	/*public static List<Transaction> showTransactions() throws BankSystemException
	{
		List<Transaction> transactions = null;
		try {
			transactions=AccountService.getAllTransactions();
		}

		catch(BankSystemException e)
		{
			MessagesNotification.showErrorMessage("Error", e.getMessage());
		}
		catch(Exception e)
		{
			MessagesNotification.showErrorMessage("Error", new BankSystemException().getMessage());
		}
		return transactions;
	}*/
}
