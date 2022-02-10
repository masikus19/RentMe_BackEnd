package application.business.service;



public interface IGuestService {


	
	boolean addFirstName(String firstName);
	boolean addLastName(String lastName);
	boolean addPhone(String phoneNumber);
	boolean addEmail(String email);
	
	
	String getFirstName();
	String getLastName();
	String getPhone();
	String getEmail();
	
	
	};
