package application.business.dto.fromFront;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EditProfileDto 
{
	@JsonInclude(Include.NON_NULL)
	String firstName;
	
	@JsonInclude(Include.NON_NULL)
	String lastName;
	
	@JsonInclude(Include.NON_NULL)
	@Pattern(regexp = "^[0-9]+$", message = "phone number must contain only digits")
	String numberTelephone;
	
	@Email
	@JsonInclude(Include.NON_NULL)
	String email;
	
	@JsonInclude(Include.NON_NULL)
	String photo;
	
	@JsonInclude(Include.NON_NULL)
	String aboutMe;
	
}
