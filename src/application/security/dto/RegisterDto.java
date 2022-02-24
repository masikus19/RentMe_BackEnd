package application.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.*;

import lombok.Getter;

@Getter
public class RegisterDto 
{		@NotNull
		@Size(min = 3, max = 50)
		@Pattern(regexp = "^[A-Za-z0-9]+$", message = "login must contain only digits and letters")
		private String login;
		
		@NotNull
		@Size(min = 5)
		private String password;
		
		@NotNull
		@Size(min = 4, max = 10)
		private String role;
		
		@NotNull
		private String firstName;
		
		@NotNull
		private String lastName;
		
		@NotNull
		@Email
		private String email;
		
		@NotNull
		@Pattern(regexp = "^[0-9]+$", message = "phone number must contain only digits")
		private String numberTelephone;
		
		@JsonInclude(Include.NON_NULL)
		private String photo;
		
		@JsonInclude(Include.NON_NULL)
		String aboutMe;
		
		
		
}
