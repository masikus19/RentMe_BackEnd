package application.business.exceptionsHandling;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.business.repositories.RenterRepository;
import application.security.services.BadRequestException;

@Component
public class Checks {
	
	@Autowired RenterRepository autowiredRenterRepo;
	
	private static RenterRepository renterRepo;
	
	public static void checkLogin(String login)
	{
		if(login==null)
			throw new BadRequestException("Wrong login");
	}
	
	@PostConstruct
	private void plugAutowired()
	{
		renterRepo = autowiredRenterRepo;
	}

}
