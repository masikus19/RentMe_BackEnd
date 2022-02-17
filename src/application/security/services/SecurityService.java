package application.security.services;

import static application.security.exceptionsHandling.AccountChecks.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import application.business.entities.Owner;
import application.business.entities.Renter;
import application.business.repositories.OwnerRepository;
import application.business.repositories.RenterRepository;
import application.security.configuration.AccountingParameters;
import application.security.dto.RegisterDto;
import application.security.entities.Account;
import application.security.repositories.AccountRepository;

@Service
public class SecurityService implements ISecurityService {
	
	@Autowired AccountRepository accountRepo;
	@Autowired PasswordEncoder encoder;
	@Autowired AccountingParameters parameters;
	@Autowired RenterRepository renterRepo;
	@Autowired OwnerRepository ownerRepo;
	
	private Account getAccount(String login) {
		checkLogin(login);
		Account account = accountRepo.findById(login).orElse(null);
		if (account == null)
			throw new BadRequestException("Login "+login+" not found");
		return account;
	}
	
	@Override
	public synchronized Account addUser(RegisterDto data) 
	{
		isAccountExists(data.getLogin());
		checkPassword(data.getPassword());
		renterRepo.save(new Renter(data.getLogin(), data.getFirstName(), data.getLastName(), data.getNumberTelephone(), data.getEmail(), data.getPhoto()));
		return accountRepo.save(new Account(data.getLogin(), encoder.encode(data.getPassword()), "ROLE_USER"));
	}
	
	@Override
	public synchronized Account addOwner(RegisterDto data) 
	{
		isAccountExists(data.getLogin());
		checkPassword(data.getPassword());
		ownerRepo.save(new Owner(data.getLogin(), data.getEmail(), data.getFirstName(), data.getLastName(), data.getNumberTelephone(), data.getAboutMe(), data.getPhoto()));
		return accountRepo.save(new Account(data.getLogin(), encoder.encode(data.getPassword()), "ROLE_OWNER"));
	}
	

	@Override
	public synchronized Account addAccount(String login, String password, String role) 
	{
		isAccountExists(login);
		checkPassword(password);
		isRoleAllowed(role);
		return accountRepo.save(new Account(login, encoder.encode(password), "ROLE_"+role));
	}
	
	@Override
	public Account grantRole(String login, String role) 
	{
		Account account = getAccount(login);
		isRoleAllowed(role);
		Set<String> roles = account.getRoles();
		String roleWithPrefix = "ROLE_" +role;
		isRoleAbsent(roles, roleWithPrefix);
		roles.add("ROLE_" +role);
		accountRepo.save(account);
		return account;
	}
	
	@Override
	public Account depriveRole(String login, String role) 
	{
		checkRole(role);
		Account account = getAccount(login);
		Set<String> roles = account.getRoles();
		if(roles.size()<2)
			throw new BadRequestException("Account "+login+" has only one role. Accounts without roles are not allowed.");
		String roleWithPrefix = "ROLE_"+role;
		if(!roles.contains(roleWithPrefix))
			throw new BadRequestException("Account "+login+" has no role "+role);
		roles.remove(roleWithPrefix);
		accountRepo.save(account);
		return account;
	}
	
	@Override
	public Set<String> getRolesByLogin(String login) 
	{
		Account account = getAccount(login);	
		return account.getRoles().stream().map(role -> role.substring(5)).collect(Collectors.toSet());
	}
	
	@Override
	public List<Account> getAllAccounts() 
	{
		return accountRepo.findAll();
	}
	
	@Override
	public Account removeAccount(String login) 
	{
		Account account = getAccount(login);
		accountRepo.deleteById(login);
		return account;
	}
	
	@Override
	public Account removeUser(String login) 
	{
		Account account = getAccount(login);
		Set<String> roles = account.getRoles();
		if((roles.size()==1 && !roles.contains("ROLE_USER")) || roles.size()>1)
			throw new BadRequestException("Removal not authorized");
		accountRepo.deleteById(login);
		return account;
	}
	
	@Override
	public Account removeOwner(String login) 
	{
		Account account = getAccount(login);
		Set<String> roles = account.getRoles();
		if((roles.size()==1 && !roles.contains("ROLE_OWNER")) || roles.size()>1)
			throw new BadRequestException("Removal not authorized");
		accountRepo.deleteById(login);
		return account;
	}
	

	@Override
	public synchronized Account changePassword(String login, String password) 
	{
		checkPassword(password);
		checkLoginAuth(login);
		Account account = getAccount(login);
		String currentPassword = account.getPassword();
		isCurrentPassword(password, currentPassword);
		LinkedList<String> oldPasswords = account.getOldPasswords();
		isUsedPassword(password, oldPasswords);
		if(oldPasswords.size()==parameters.getOldPasswordNum()) oldPasswords.removeFirst();
		oldPasswords.addLast(currentPassword);		
		account.setPassword(encoder.encode(password));
		accountRepo.save(account);
		return account;
	}
	
	@Override
	public Account revokeAccount(String login)
	{
		Account account = getAccount(login);
		isAccountActive(account);
		account.setRevoked(true);
		accountRepo.save(account);
		return account;
	}
	
	
	@Override
	public Account activateAccount(String login) 
	{
		Account account = getAccount(login);
		isAccountRevoke(account);
		account.setRevoked(false);
		accountRepo.save(account);
		return account;
	}
	



	
}
