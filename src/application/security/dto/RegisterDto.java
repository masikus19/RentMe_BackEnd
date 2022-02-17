package application.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.*;

import lombok.Getter;

@Getter
public class RegisterDto 
{
		@Size(min = 3, max = 50)
		@Pattern(regexp = "[A-Za-z0-9]+")
		private String login;
		
//		@JsonInclude(Include.NON_NULL)
		@Size(min = 5)
		private String password;
		
		@Size(min = 4, max = 10)
		private String role;
		
		private String firstName;
		private String lastName;
		
		@Email
		private String email;
		private String numberTelephone;
		private String photo;
		@JsonInclude(Include.NON_NULL)
		String aboutMe;
		
		
		
}
