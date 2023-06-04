package pilot.primefaces;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "ALL_USERS")
public class User {
	@Id
    @GeneratedValue
	private int id;
	@Column(name="name")
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User() {

	 }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
