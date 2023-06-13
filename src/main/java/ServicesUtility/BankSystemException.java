package ServicesUtility;

import BeansUtility.MessagesNotification;

public class BankSystemException extends Exception{

	public BankSystemException() {
		super("something went wrong please try again later");
		// TODO Auto-generated constructor stub
	}

	public BankSystemException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public BankSystemException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public BankSystemException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
}
