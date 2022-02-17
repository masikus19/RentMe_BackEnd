package application.security.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.security.dto.AccountDto;
import application.security.dto.AuthPair;
import application.security.dto.Mapper;
import application.security.dto.RegisterDto;
import application.security.services.ISecurityService;
import static application.config.API.*;

@RestController
@RequestMapping(SECURITY)
@Validated
public class SecurityController {
	
	@Autowired ISecurityService service;
	
	@PostMapping(ADD_USER)
	public AccountDto addUser(@RequestBody RegisterDto data) {
		return Mapper.accountDto(service.addUser(data));
	};
	
	@PostMapping(ADD_OWNER)
	public AccountDto addOwner(@RequestBody RegisterDto data) {
		return Mapper.accountDto(service.addOwner(data));
	};
	
//	@PostMapping(ADD_ACCOUNT)
//	public AccountDto addAccount(@RequestBody AuthPair authPair, String role) {
//		return Mapper.accountDto(service.addAccount(authPair.getLogin(), authPair.getPassword(), role));
//	};
	
//	@PostMapping(ADD_ACCOUNT)
//	public AccountDto addAccount(@Valid @RequestBody RegisterDto regDto) {
//		return Mapper.accountDto(service.addAccount(regDto.getLogin(), regDto.getPassword(), regDto.getRole()));
//	};
	
	@PostMapping(ADD_ACCOUNT)
	public AccountDto addAccount(@Valid @Pattern(regexp = "^[0-9]+$") String login,
			@Valid @Pattern(regexp = "[0-9]+") String password,
			String role) {
		return Mapper.accountDto(service.addAccount(login, password, role));
	};

	@PutMapping(GRANT_ROLE)
	public AccountDto grantRole(String login, String role) {
		return Mapper.accountDto(service.grantRole(login, role));
	};
	
	@PutMapping(DEPRIVE_ROLE)
	public AccountDto depriveRole(String login, String role) {
		return Mapper.accountDto(service.depriveRole(login, role));
	};
	
	@GetMapping(GET_ALL_ACCOUNTS)
	public List<AccountDto> getAllAccounts(){
		return Mapper.accountDtoList(service.getAllAccounts());
	};
	
	@GetMapping(GET_ROLES_BY_LOGIN)
	public Set<String> getRolesByLogin(String login)
	{
		return service.getRolesByLogin(login);
	}

	@DeleteMapping(REMOVE_ACCOUNT)
	public AccountDto removeAccount(String login) {
		return Mapper.accountDto(service.removeAccount(login));
	};

	@DeleteMapping(REMOVE_USER)
	public AccountDto removeUser(String login) {
		return Mapper.accountDto(service.removeUser(login));
	};
	
	@DeleteMapping(REMOVE_OWNER)
	public AccountDto removeOwner(String login) {
		return Mapper.accountDto(service.removeOwner(login));
	}
	
	@PutMapping(CHANGE_PASSWORD)
	public AccountDto changePassword(@RequestBody AuthPair authPair) {
		return Mapper.accountDto(service.changePassword(authPair.getLogin(), authPair.getPassword()));
	};
	
	@PutMapping(REVOKE_ACCOUNT)
	public AccountDto revokeAccount(@Size(min = 3, max = 50) @Pattern(regexp = "^[A-Za-z0-9]+$") String login) {
		return Mapper.accountDto(service.revokeAccount(login));
	};
	
	@PutMapping(ACTIVATE_ACCOUNT)
	public AccountDto activateAccount(String login) {
		return Mapper.accountDto(service.activateAccount(login));
	};

}
