package application.security.services;

@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException{
	
	public BadRequestException(String msg) {
		super(msg);
	}
}
