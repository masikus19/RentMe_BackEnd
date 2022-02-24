package application.security.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class AddAccountDto 
{
	@NotNull
	@Size(min = 3, max = 50)
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "login must contain only digits and letters")
	private String login;
	
	@NotNull
	@Size(min = 5)
	private String password;
	
	@NotNull
	@Size(min = 4, max = 10)
	private String role;

}
