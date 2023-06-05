package pilot.primefaces;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table (name = "Client")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private String name;
	private String password;
	//private Date date;
	private int netSalary;
	private String address;
	private String mobile;
	private String mail;
	
	public Client()
	{}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}*/
	public int getNetSalary() {
		return netSalary;
	}
	public void setNetSalary(int netSalary) {
		this.netSalary = netSalary;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

}
