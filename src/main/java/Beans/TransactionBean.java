package Beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import BeansUtility.ExceptionLogger;
import BeansUtility.MessagesNotification;
import BeansUtility.TransactionsTable;
import ORMs.Transaction;
import Services.AccountService;
import Services.TransactionService;
import ServicesUtility.BankSystemException;
import ServicesUtility.ClientServiceUtility;
import ServicesUtility.TransactionServiceUtility;

@ManagedBean(name="transaction")
@SessionScoped
public class TransactionBean {
	String selectedStatus="pending";
	private Transaction selectedTransaction;

	
	public TransactionBean() { }
	
	public List<Transaction> showTransactions()
	{
		List<Transaction> transactions = null;
		try {
			transactions = TransactionServiceUtility.showAllTransactions();
			
		} catch (BankSystemException e) {
			ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage("failed to load table",new BankSystemException().getMessage());
		}
		catch (Exception e) {
			ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage("Error happened",new BankSystemException().getMessage());
		}
		return transactions;
	}

	public void submit()
	{
		try {
			TransactionService.saveChangesToTransaction(selectedStatus,selectedTransaction);
			//MessagesNotification.showDoneMessage("transaction "+selectedStatus,"notification sent to "+selectedTransaction.getClient().getName());

			System.out.println("selectedTransaction "+selectedTransaction.getDescription());
			System.out.println("selectedTransaction "+selectedTransaction.getId());
		}
		catch(BankSystemException e)
		{
			MessagesNotification.showErrorMessage("error occured while saving changes",e.getMessage());
		}
		catch(Exception e)
		{
	        ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage("error happened",new BankSystemException().getMessage());
		}
	}
	public void selectPending() 
	{
		selectedStatus="pending";
		submit();
	}
	
	public void selectApproved()
	{
		selectedStatus="approved";
		submit();
	}
	
	public void selectRejected()
	{
		selectedStatus="rejected";
		submit();
	}
	
	public String getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(String selectedStatus) {
			this.selectedStatus = selectedStatus;
	}

	public Transaction getSelectedTransaction() {
	    return selectedTransaction;
	}

	public void setSelectedTransaction(Transaction selectedTransaction) {
	    this.selectedTransaction = selectedTransaction;
	}
}
