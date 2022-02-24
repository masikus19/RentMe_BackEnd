package application.business.dto.toFront;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RenterDto 
{
	String login;
	String firstName;
	String lastName;
	String numberTelephone;
	@Email
	String email;
	String photo;
}
