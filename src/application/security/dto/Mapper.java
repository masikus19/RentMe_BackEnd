package application.security.dto;

import java.util.List;
import java.util.stream.Collectors;

import application.security.entities.Account;

public class Mapper {
	
	public static AccountDto accountDto(Account account) {
		return new AccountDto(account.getLogin(), account.getRoles().stream()
                                                                    .map(role->role.substring(5))
                                                                    .collect(Collectors.toSet()));
	}
	
	public static List<AccountDto> accountDtoList(List<Account> accountList) {
		return accountList.stream().map(account -> accountDto(account)).collect(Collectors.toList());
	}
}
