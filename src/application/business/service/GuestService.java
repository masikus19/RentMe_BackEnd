package application.business.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuestService implements IGuestService {

	
	private String FirstName ="";
	private String LastName = "";
	private String Phone = "";
	private String Email = "";
	
	@Override
	public boolean addFirstName(String firstName) {
		
		if(firstName == null || firstName.isEmpty()) return false;
		if(isDigit(firstName) || !isLetter(firstName)) return false;
		FirstName = firstName;
		return true;
	}

	@Override
	public boolean addLastName(String lastName) {
		
		if(lastName == null || lastName.isEmpty()) return false;
		if(isDigit(lastName) || !isLetter(lastName)) return false;
		LastName = lastName;
		return true;
	}

	@Override
	public boolean addPhone(String phoneNumber) {
		
		if(phoneNumber == null || phoneNumber.isEmpty()) return false;
		if (!isDigit(phoneNumber)) return false;
		Phone = phoneNumber;
		return true;
	}

	@Override
	public boolean addEmail(String email) {
		if(email == null || email.isEmpty()) return false;
		if(!isValid(email)) return false;
		Email = email;
		return true;
	}

	@Override
	public String getFirstName() {
		return FirstName;
	}

	@Override
	public String getLastName() {
		
		return LastName;
	}

	@Override
	public String getPhone() {
		
		return Phone;
	}

	@Override
	public String getEmail() {
		
		return Email;
	}
	
	

	public static boolean isDigit(String str)
	{
		return str.matches("\\d+");
	}
	
	public static boolean isLetter(String str)
	{
		return str.matches("[A-Za-z_]\\w*");
	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		public static boolean isValid(String email) {
		        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
		        return matcher.find();
		}


	

}
