package Beans;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pilot.primefaces.Client;

@ManagedBean(name = "signup")
@SessionScoped
public class SignupBean {
	private String username;
	private String password;
	private boolean isEmployee=false;
	private String mobileNumber;
	private String mail;
	private String selectedOption;
	
	
	public String getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}

	public SignupBean() {
		
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
	public boolean isEmployee() {
		return isEmployee;
	}
	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
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
	
	public void submit()
	{
		boolean isPassword;
	   if( password.matches("(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*_]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$"))
		   isPassword=true;
	   else
		   isPassword=false;	
	   String regex = "^[A-Za-z0-9+_.-]+@+[A-Za-z0-9+_.-]+(.+)$";
	   boolean isEmail;
	   Pattern pattern = Pattern.compile(regex);
	   Matcher matcher = pattern.matcher(mail);
	   if(matcher.matches())
			isEmail=true;
	   else
		   isEmail=false;	
	   
	   if(isPassword && isEmail)
	   {
		   Client client= new Client();
		   client.setName(username);
		   client.setPassword(password);
		   client.setMobile(mobileNumber);
		   client.setMail(mail);
		   client.setAddress(selectedOption);
		   System.out.println("done");
		   save(client);
		   done();
	   }
		  
	}
	public void save(Client client)
	{
		//create a service
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(client);
		session.getTransaction().commit();
		session.close();
	}
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void done() {
        addMessage(FacesMessage.SEVERITY_INFO, "Done", "data is saved");
    }
	
	//growl
	
}