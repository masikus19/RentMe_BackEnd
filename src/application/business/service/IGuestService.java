package application.business.service;



public interface IGuestService {


	
	boolean AddFirstName(String firstName);
	boolean AddLastName(String lastName);
	boolean AddPhone(String phoneNumber);
	boolean AddEmail(String email);
	
	
	String GetFirstName();
	String GetLastName();
	String GetPhone();
	String GetEmail();
	
	
	};
