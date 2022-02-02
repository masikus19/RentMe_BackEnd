package application.security.services;

import java.util.List;
import java.util.Set;

import application.security.entities.Account;

public interface ISecurityService {

	Account addUser(String login, String password);
	Account addOwner(String login, String password);
	Account addAccount(String login, String password, String role);
	Account grantRole(String login, String role);
	Account depriveRole(String login, String role);
	Set<String> getRolesByLogin(String login);
	List<Account> getAllAccounts();
	Account removeAccount(String login);
	Account removeUser(String login);
	Account removeOwner(String login);
	
	Account changePassword(String login, String password);
	Account revokeAccount(String login);
	Account activateAccount(String login);
	

	

}