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
import org.hibernate.cfg.Configuration;


import BeansUtility.MessagesNotification;
import ORMs.Account;
import ORMs.Client;
import Services.SignUpService;

@ManagedBean(name = "signup")
@ViewScoped
public class SignupBean {
	private String username;
	private String password;
	private String mobileNumber;
	private String mail;
	private int salary;
	private String selectedOption;
	private String languageCode;
	private static Map<String,Object> countries;
	
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
	        		    FacesContext.getCurrentInstance()
	        			.getViewRoot().setLocale((Locale)entry.getValue());
	        	   }
	          }
	}
	

	public String submit()
	{
		String page="";
		try {
			SignUpService.saveClientData(createClient());
			MessagesNotification.showDoneMessage("Done","data is saved to the database");
			//Thread.sleep(2000);
			UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
			page = view.getViewId() + "?faces-redirect=true";
			return page; 
		}
		catch(Exception e)
		{
			MessagesNotification.showErrorMessage("Registration Failed",e.getMessage());
		}
		return page;
	}
	
	public String goToSignIn()
	{
		String page="";
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    try {
	        externalContext.redirect(externalContext.getRequestContextPath() + "/signIn.xhtml");
	    } catch (IOException e) {
	        e.printStackTrace();
	        // Handle the exception if necessary
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
		return client;
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