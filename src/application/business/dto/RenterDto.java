package application.business.dto;

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
	String email;
	String photo;
}
