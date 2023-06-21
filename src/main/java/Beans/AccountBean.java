package Beans;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import BeansUtility.ClientLogOut;
import BeansUtility.ClientLogin;
import BeansUtility.ExceptionLogger;
import BeansUtility.MessagesNotification;
import ORMs.Client;
import ORMs.Transaction;
import Services.AccountService;
import ServicesUtility.BankSystemException;
import ServicesUtility.ClientServiceUtility;
import ServicesUtility.Languages;
import ServicesUtility.TransactionServiceUtility;

//create module dialiag for transaction

@ManagedBean(name="account")
@SessionScoped
public class AccountBean {
	private String username;
	private int amountOfMoney;
	private String address;
	private String mail;
	private String mobileNumber;
	private Transaction selectedTransaction;
	private String addressTextField;
	public AccountBean()
	{
		
	}
	
	public void submit() throws BankSystemException
	{
		try {
			AccountService.editClientDataAndAccount(address,mail,mobileNumber,amountOfMoney);
			MessagesNotification.showDoneMessage(Languages.createBundle().getString("message_Done"), "Done");
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
	    catch (Exception e) {
			ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage("Error happened",new BankSystemException().getMessage());
		}
	    return page;
	}
	
	public void logOut()
	{
		ClientLogOut.logOutCurrentClient();
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
		catch (Exception e) {
			ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage("Error happened",new BankSystemException().getMessage());
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
		catch (Exception e) {
			ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage("Error happened",new BankSystemException().getMessage());
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
		catch (Exception e) {
			ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage("Error happened",new BankSystemException().getMessage());
		}
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getAmountOfMoney() {
		int amountOfMoney=0;
		try {
		 amountOfMoney=AccountService.sendAccountToView().getAmount();
		}
		catch(BankSystemException e)
		{
			MessagesNotification.showErrorMessage("money failed to load",e.getMessage());
		}
		catch (Exception e) {
			ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage("Error happened",new BankSystemException().getMessage());
		}
		return amountOfMoney;
	}

	public void setAmountOfMoney(int amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	public Transaction getSelectedTransaction() {
		return selectedTransaction;
	}

	public void setSelectedTransaction(Transaction selectedTransaction) {
		this.selectedTransaction = selectedTransaction;
	}

	public String getAddressTextField() {
		String addressTextField="";
		try {
			 addressTextField=Languages.createBundle(ClientLogin.getClient()).getString("message_address");
		} catch (BankSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addressTextField;
	}

	public void setAddressTextField(String addressTextField) {
		this.addressTextField = addressTextField;
	}
}
