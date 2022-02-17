package application.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import application.security.services.ISecurityService;

@RestController
@RequestMapping("/security")
public class SecurityController {
	
	@Autowired ISecurityService service;
	
	@PostMapping("/addUser")
	public AccountDto addUser(@RequestBody AuthPair authPair) {
		return Mapper.accountDto(service.addUser(authPair.getLogin(), authPair.getPassword()));
	};

	@PostMapping("/addAccount")
	public AccountDto addAccount(@RequestBody AuthPair authPair, String role) {
		return Mapper.accountDto(service.addAccount(authPair.getLogin(), authPair.getPassword(), role));
	};

	@PutMapping("/grantRole")
	public AccountDto grantRole(String login, String role) {
		return Mapper.accountDto(service.grantRole(login, role));
	};
	
	@PutMapping("/depriveRole")
	public AccountDto depriveRole(String login, String role) {
		return Mapper.accountDto(service.depriveRole(login, role));
	};
	
	@GetMapping("/getAllAccounts")
	public List<AccountDto> getAllAccounts(){
		return Mapper.accountDtoList(service.getAllAccounts());
	};

	@DeleteMapping("/removeAccount")
	public AccountDto removeAccount(String login) {
		return Mapper.accountDto(service.removeAccount(login));
	};

	@DeleteMapping("/removeUser")
	public AccountDto removeUser(String login) {
		return Mapper.accountDto(service.removeUser(login));
	};
	
	@PostMapping("/changePassword")
	public AccountDto changePassword(@RequestBody AuthPair authPair) {
		return Mapper.accountDto(service.changePassword(authPair.getLogin(), authPair.getPassword()));
		
	}
	
	
	@PutMapping("/revokeAccount")
	public AccountDto revokeAccount(String login) {
		return Mapper.accountDto(service.revokeAccount(login));
		
	}
	
	
	@PutMapping("/activateAccount")
	public	AccountDto activateAccount(String login){
		return Mapper.accountDto(service.activateAccount(login));
	}

}
