package Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="transaction")
@SessionScoped
public class TransactionBean {
	String selectedStatus;
	public TransactionBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void selectPending()
	{
		selectedStatus="pending";
	}
	
	public void selectApproved()
	{
		selectedStatus="approved";
	}
	
	public void selectRejected()
	{
		selectedStatus="rejected";
	}

	public String getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(String selectedStatus) {
		this.selectedStatus = selectedStatus;
	}
}
