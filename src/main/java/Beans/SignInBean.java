package Beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import BeansUtility.ExceptionLogger;
import BeansUtility.MessagesNotification;
import ORMs.Client;
import Services.SignInService;
import Services.SignUpService;
import ServicesUtility.BankSystemException;


@ManagedBean(name="signin")
@SessionScoped
public class SignInBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	//returns the page name to the view (xhtml)
	
	public String submit() throws BankSystemException 
	{
		String page="";
		try 
		{
			page=SignInService.checkSignInData(username,password);
			MessagesNotification.showDoneMessage("login successfully","Welcome "+username);
		}
		catch(BankSystemException e)
		{
			MessagesNotification.showErrorMessage("login failed try again",e.getMessage());
		}
		catch(Exception e)
		{
			ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage("login failed",new BankSystemException().getMessage());
		}
		return page;
	}
	
	public String goToSignUp()
	{
		String page="";
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    try {
	        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
	    } catch (Exception e) {
	    	ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage("page Failed to load",new BankSystemException().getMessage());

	    }
	    return page;
	}
	
	public SignInBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
