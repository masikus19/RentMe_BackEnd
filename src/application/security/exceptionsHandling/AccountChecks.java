package application.security.exceptionsHandling;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import application.security.configuration.AccountingParameters;
import application.security.entities.Account;
import application.security.repositories.AccountRepository;
import application.security.services.BadRequestException;

@Component
public class AccountChecks 
{
	@Autowired PasswordEncoder autowiredEncoder;
	@Autowired AccountRepository autowiredAccountRepo;
	@Autowired AccountingParameters autowiredParametres;
	
	private static AccountRepository accountRepo;
	private static PasswordEncoder encoder;
	private static AccountingParameters parameters;
	private static List<String> allowedRoles;
	
	public static void isAccountRevoke(Account account) 
	{
		if(!account.isRevoked())
			throw new BadRequestException("Account active");	
	}

	public static void isUsedPassword(String password, LinkedList<String> oldPasswords) 
	{
		if(!oldPasswords.stream().noneMatch(hash->encoder.matches(password, hash)))
			throw new BadRequestException("Password had been used");
		
	}

	public static void isCurrentPassword(String password, String currentPassword) 
	{
		if(encoder.matches(password, currentPassword))
			throw new BadRequestException("Password is using now");		
	}
	
	public static void checkLogin(String login)
	{
		if(login==null)
			throw new BadRequestException("Wrong login");
	}
	
	public static void checkPassword(String password) 
	{
		if(password==null)
			throw new BadRequestException("Wrong password");	
	}
	
	public static void checkRole(String role) 
	{
		if(role==null)
			throw new BadRequestException("Wrong role");			
	}
	
	public static void isAccountExists(String login) 
	{
		checkLogin(login);
		if(accountRepo.existsById(login))
			throw new BadRequestException("Duplicated login "+login);	
	}

	public static void isRoleAllowed(String role) 
	{
		checkRole(role);
		if(!allowedRoles.contains(role))
			throw new BadRequestException("Role not allowed");	
	}
	
	
	public static void isRoleAbsent(Set<String> roles, String role) 
	{
		if(roles.contains(role))
			throw new BadRequestException("Role is present");
	}

	
	public static void isAccountActive(Account account) 
	{
		if(account.isRevoked())
			throw new BadRequestException("Account revoked");	
	}
	
	public static void checkLoginAuth(String login){
		if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(login))
			throw new BadRequestException("You have no rights for change password");
	}
	
	@PostConstruct
	private void plugAutowired()
	{
		accountRepo = autowiredAccountRepo;
		encoder = autowiredEncoder;
		parameters = autowiredParametres;
		allowedRoles = Arrays.asList(parameters.getAllowedRoles());
	}

}
