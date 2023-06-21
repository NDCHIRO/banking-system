package Beans;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.ViewHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.facelets.ValidatorHandler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.Log;
import org.hibernate.cfg.Configuration;


import BeansUtility.ExceptionLogger;
import BeansUtility.LanguagesInfo;
import BeansUtility.MessagesNotification;
import ORMs.Account;
import ORMs.BankEmployee;
import ORMs.Client;
import Services.SignUpService;
import ServicesUtility.BankSystemException;
import ServicesUtility.Languages;

@ManagedBean(name = "signup")
@SessionScoped
public class SignupBean {
	private String username;
	private String password;
	private String mobileNumber;
	private String mail;
	private int salary;
	private String selectedOption;
	private String languageCode;
	private String selectedLanguage;
	private static Map<String,Object> countries;
	
	
	 //private ResourceBundle bundle;
	static {
		countries = new LinkedHashMap<String,Object>();
		countries.put("English", Locale.ENGLISH);
		countries.put("Arabic", new Locale("ar","EG"));
	}

	public SignupBean() {
		
	}
	
	public void countryLocaleCodeChanged(ValueChangeEvent take_event)
	{
			String new_language_code = take_event.getNewValue().toString();
	        for (Map.Entry<String, Object> entry : countries.entrySet()) 
	        {
	        	   if(entry.getValue().toString().equals(new_language_code))
	        	   {
	        		   selectedLanguage = new_language_code;		// options: ar_EG , en
	        		    FacesContext.getCurrentInstance()
	        			.getViewRoot().setLocale((Locale)entry.getValue());
	        	   }
	          }
	}
	

	public String submit() throws BankSystemException
	{
		String page="";
		System.out.println("hellooo "+LanguagesInfo.selectedLanguage);
		Client client = null;
		try 
		{
			client = createClient();
			SignUpService.saveClientData(client);
			
			reset();
			UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
			page = view.getViewId() + "?faces-redirect=true";
			MessagesNotification.showDoneMessage("Done","data is saved to the database");
			return page; 
		}
		catch(BankSystemException e)
		{
			MessagesNotification.showErrorMessage(Languages.createBundle(client).getString("error_registrationFailed"),e.getMessage());
				
		}
		catch(Exception e)
		{
	        ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage(Languages.createBundle(client).getString("error_registrationFailed"),new BankSystemException().getMessage());
		}
		return page;
	}
	
	
	public void reset() {  
	         setUsername("");
	         setMail("");
	         setPassword("");
	         setMobileNumber("");
	         
	   } 
	
	public String goToSignIn()
	{
		String page="";
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    try {
	        externalContext.redirect(externalContext.getRequestContextPath() + "/signIn.xhtml");
	    } catch (Exception e) {
	    	ExceptionLogger.logException(e);
			MessagesNotification.showErrorMessage("page Failed to load",new BankSystemException().getMessage());

	    }
	    return page;
	}
	public Client createClient()
	{
		Client client = new Client();
		client.setName(username);
		client.setPassword(password);
		client.setMobile(mobileNumber);
		client.setMail(mail);
		client.setRole(selectedOption);
		client.setNetSalary(salary);
		client.setSelectedLanguage(selectedLanguage);
		return client;
	}
	
	public BankEmployee createEmployee()
	{
		BankEmployee bankEmployee = new BankEmployee();
		bankEmployee.setName(username);
		bankEmployee.setPosition("employee");
		return bankEmployee;
	}
	
 
	 public String getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
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
	
	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public  Map<String, Object> getCountries() {
		return countries;
	}

	public  void setCountries(Map<String, Object> countries) {
		SignupBean.countries = countries;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}



}