package application.security.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;


@Getter
public class AuthPair {
	
	private String login;
	
	@NotNull
	@Size(min = 5)
	private String password;

}
