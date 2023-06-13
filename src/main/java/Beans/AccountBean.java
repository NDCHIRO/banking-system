package Beans;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import BeansUtility.ClientLogin;
import BeansUtility.ExceptionLogger;
import BeansUtility.MessagesNotification;
import ORMs.Client;
import ORMs.Transaction;
import Services.AccountService;
import ServicesUtility.BankSystemException;
import ServicesUtility.TablesSearch;

//create module dialiag for transaction

@ManagedBean(name="account")
@SessionScoped
public class AccountBean {
	private String username;
	private String transferedUsername;
	private int amountOfMoney;
	private int transferedMoney;
	private String address;
	private String mail;
	private String mobileNumber;
	public AccountBean()
	{
		
	}
	
	public void submit() throws BankSystemException
	{
		try {
			AccountService.editClientDataAndAccount(address,mail,mobileNumber,amountOfMoney);
			MessagesNotification.showDoneMessage("data edited successfully", "Done");
		}
		catch (BankSystemException e) {
			MessagesNotification.showErrorMessage("data failed to load",e.getMessage());
			//throw new BankSystemException(e.getMessage());
		} 
		catch (Exception e) {
			ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage("Error happened",new BankSystemException().getMessage());
		}
	}
	
	public String goToMoneyTransferPage()
	{
		String page="";
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    try {
	        externalContext.redirect(externalContext.getRequestContextPath() + "/moneyTransfer.xhtml");
	    } catch (IOException e) {
	    	ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage("data failed to load",new BankSystemException().getMessage());
	    }
	    return page;
	}
	
	public List<Transaction> showTransactions() throws BankSystemException
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
	}
	
	public void transferMoney() throws BankSystemException
	{
		try
		{
			AccountService.transferMoney(transferedMoney,transferedUsername);
			MessagesNotification.showDoneMessage("Done", "Money sent");

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
	
	public String getUsername() {
		return username;
	}
	public String getAddress()
	{
		String address="";
		try {
			address = ClientLogin.getClient().getAddress();
		} catch (BankSystemException e) {
			MessagesNotification.showErrorMessage("address failed to load",e.getMessage());
		}
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMail() {
		String mail="";
		try {
			mail = ClientLogin.getClient().getMail();
		} catch (BankSystemException e) {
			MessagesNotification.showErrorMessage("mail failed to load",e.getMessage());
		}
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMobileNumber() {
		String mobileNumber="";
		try {
			mobileNumber = ClientLogin.getClient().getMobile();
		} catch (BankSystemException e) {
			MessagesNotification.showErrorMessage("mobile number failed to load",e.getMessage());
		}
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getTransferedUsername() {
		return transferedUsername;
	}

	public void setTransferedUsername(String transferedUsername) {
		this.transferedUsername = transferedUsername;
	}

	public int getAmountOfMoney() throws Exception {
		return amountOfMoney;
	}

	public void setAmountOfMoney(int amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	public int getTransferedMoney() {
		return transferedMoney;
	}

	public void setTransferedMoney(int transferedMoney) {
		this.transferedMoney = transferedMoney;
	}
}
