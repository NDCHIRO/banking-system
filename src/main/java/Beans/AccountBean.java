package Beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import BeansUtility.ClientLogin;
import BeansUtility.MessagesNotification;
import ORMs.Client;
import ORMs.Transaction;
import Services.AccountService;
import Services.BankSystemException;
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
	
	public void submit() throws Exception
	{
		try {
			AccountService.editClientDataAndAccount(address,mail,mobileNumber,amountOfMoney);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankSystemException();
		}
	}
	
	public List<Transaction> showTransactions() throws Exception
	{
		List<Transaction> transactions;
		try {
			transactions=AccountService.getAllTransactions();
		}
		catch(Exception e)
		{
			throw new BankSystemException();
		}
		return transactions;
	}
	
	public void transferMoney() throws Exception
	{
		try {
			AccountService.transferMoney(transferedMoney,transferedUsername);
		}
		catch(Exception e)
		{
			MessagesNotification.showErrorMessage("Error", e.getMessage());
			//throw new BankSystemException();
		}
	}
	
	public String getUsername() {
		return username;
	}
	public String getAddress() {
		return ClientLogin.getClient().getAddress();
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMail() {
		return ClientLogin.getClient().getMail();
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMobileNumber() {
		return ClientLogin.getClient().getMobile();
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
