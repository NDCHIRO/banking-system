package Beans;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import BeansUtility.MessagesNotification;
import ORMs.Client;
import Services.SignUpService;

@ManagedBean(name = "signup")
@ViewScoped
public class SignupBean {
	private String username;
	private String password;
	private String mobileNumber;
	private String mail;
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
	

	public void submit()
	{
		try {
			SignUpService.saveClientData(createClient());
			MessagesNotification.showDoneMessage("Done","data are saved to the database");
		}
		catch(Exception e)
		{
			MessagesNotification.showErrorMessage("Registration Failed",e.getMessage());
		}
	}
	
	
	
	public Client createClient()
	{
		Client client = new Client();
		client.setName(username);
		client.setPassword(password);
		client.setMobile(mobileNumber);
		client.setMail(mail);
		client.setRole(selectedOption);
		System.out.println("done");
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
	
	

}