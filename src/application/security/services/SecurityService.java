package application.security.services;

import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import application.security.api.SecurityApi;
import application.security.entities.Account;
import application.security.repositories.AccountMongoRepository;

@Transactional
@Service
public class SecurityService implements ISecurityService {
	
	@Autowired AccountMongoRepository accountRepo;
	@Autowired PasswordEncoder encoder;

	@Override
	public Account addUser(String login, String password) {
		if (accountRepo.existsById(login))
			throw new BadRequestException(SecurityApi.ACCOUNT_ALREADY_EXISTS +  " " + login);
		return accountRepo.save(new Account(login, encoder.encode(password), SecurityApi.USER));
		
	}
	@Override
	public Account addAccount(String login, String password, String role) {
		Account account = accountRepo.findById(login).orElse(null);
		if(account==null)
			throw new BadRequestException(SecurityApi.ACCOUNT_DOESNT_EXIST);
		return accountRepo.save(new Account(login, encoder.encode(password), role));
	}
	@Override
	public Account grantRole(String login, String role) {
		return setRoleState(login, role, true);
	}
	@Override
	public Account depriveRole(String login, String role) {
		return setRoleState(login, role, false);
	}
	@Override
	@Transactional(readOnly=true)
	public Set<String> getRolesByLogin(String login) {
		return getAccount(login).getRoles().stream().map(role->role.substring(5)).collect(Collectors.toSet());
	}
	@Override
	@Transactional(readOnly=true)
	public List<Account> getAllAccounts() {
		
		return accountRepo.findAll();
	}
	@Override
	public Account removeAccount(String login) {
		Account account = getAccount(login);
		accountRepo.deleteById(login);
		return account;
	}
	@Override
	public Account removeUser(String login) {
		
		Account account = getAccount(login);
		Set<String> roles = account.getRoles();
		if(roles.contains(SecurityApi.ADMIN) || roles.contains(SecurityApi.MANAGER))
			throw new BadRequestException(SecurityApi.REMOVAL_DENIED);
		else accountRepo.deleteById(login);
		return account;
	}
	
	@Override
	public Account addOwner(String login, String password) {
		Account account = accountRepo.findById(login).orElse(null);
		if(account==null)
			throw new BadRequestException(SecurityApi.ACCOUNT_DOESNT_EXIST);
		return accountRepo.save(new Account(login, encoder.encode(password), SecurityApi.OWNER));
	}
	@Override
	public Account removeOwner(String login) {
		Account account = getAccount(login);
		Set<String> roles = account.getRoles();
		if(roles.contains(SecurityApi.ADMIN) || roles.contains(SecurityApi.MANAGER))
			throw new BadRequestException(SecurityApi.REMOVAL_DENIED);
		else accountRepo.deleteById(login);
		return account;
	}
	
	
	@Override
	public Account changePassword(String login, String password) {
		Account account = getAccount(login);
		if(account==null) return account;

		if(account.getPassword().equals(password))
			throw new BadRequestException(SecurityApi.PASSWORD_HAD_BEEN_ALREADY_USED);
		else
			 account.setPassword(password);
			
		return account;
	}
	@Override
	public Account revokeAccount(String login) {	
		return setRevokedState(login, true);
	}
	@Override
	public Account activateAccount(String login) {
		return setRevokedState(login, false);		 
	}
	
	private Account setRevokedState(String login, boolean state)
	{
		Account account = getAccount(login);
		if(account==null) return account;
		
		if(!account.isRevoked())
			throw new BadRequestException(SecurityApi.ACCOUNT_HAD_BEEN_REVOKED);
		
		account.setRevoked(state);
		accountRepo.save(account);
		return account;
	}
	
	private Account getAccount(String login)
	{
		Account account = accountRepo.findById(login).orElse(null);
		if(account==null)
			throw new BadRequestException(SecurityApi.ACCOUNT_DOESNT_EXIST);
		return account;
	}
	
	
	private Account setRoleState(String login, String role,  boolean remove)
	{
		Account account = getAccount(login);
		if(account==null) return account;
		
		// remove = true - delete , remove = false add
		if(remove)
		{
			if(!account.getRoles().contains(role))
				
				throw new BadRequestException(SecurityApi.ROLE_DOESNT_PRESENT);
			else
			{
				account.getRoles().remove(role);
				accountRepo.save(account);
			}
		}
		else
		{
			if(account.getRoles().contains(role))
				
				throw new BadRequestException(SecurityApi.ROLE_IS_ALREADY_PRESENT);
			else
			{
				account.getRoles().add(role);
				accountRepo.save(account);
			}
		}

	
		return account;
	}
	
}
