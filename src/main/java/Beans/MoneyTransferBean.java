package Beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import BeansUtility.ClientLogOut;
import BeansUtility.ExceptionLogger;
import BeansUtility.MessagesNotification;
import ORMs.Transaction;
import Services.AccountService;
import ServicesUtility.BankSystemException;
import ServicesUtility.TransactionServiceUtility;
@ManagedBean(name="moneyTransfer")
@SessionScoped
public class MoneyTransferBean {
	private String transferedUsername;
	private int transferedMoney;
	public List<Transaction> showTransactions() throws BankSystemException
	{
		List<Transaction> transactions = null;
		try {
			transactions=TransactionServiceUtility.showAllTransactions();
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
	}
	
	public void transferMoney() throws BankSystemException
	{
		try
		{
			AccountService.transferMoney(transferedMoney,transferedUsername);
			MessagesNotification.showDoneMessage("request sent", "waiting for the acceptance of the bank");

		}
		catch(BankSystemException e)
		{
			MessagesNotification.showErrorMessage("Error", e.getMessage());
		}
		catch(Exception e)
		{
			ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage("Error", new BankSystemException().getMessage());
		}
	}
	
	public void logOut()
	{
		ClientLogOut.logOutCurrentClient();
	}
	
	
	public String getTransferedUsername() {
		return transferedUsername;
	}

	public void setTransferedUsername(String transferedUsername) {
		this.transferedUsername = transferedUsername;
	}

	public int getTransferedMoney() {
		return transferedMoney;
	}

	public void setTransferedMoney(int transferedMoney) {
		this.transferedMoney = transferedMoney;
	}

}
