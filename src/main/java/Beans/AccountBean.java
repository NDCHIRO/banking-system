package Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import Services.AccountService;

@ManagedBean(name="account")
@SessionScoped
public class AccountBean {
	private String username;
	private String address;
	private String mail;
	private String mobileNumber;
	public AccountBean()
	{
		
	}
	
	public void submit()
	{
		AccountService.getCurrentClient();
	}
	public String getUsername() {
		return username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}
