package application.business.service;



public interface IGuestService {


	boolean addLogin(String login);
	boolean addFirstName(String firstName);
	boolean addLastName(String lastName);
	boolean addPhone(String phoneNumber);
	boolean addEmail(String email);
	
	String getLogin();
	String getFirstName();
	String getLastName();
	String getPhone();
	String getEmail();
	
	
	};
