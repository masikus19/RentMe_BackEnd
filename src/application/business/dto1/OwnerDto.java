package application.business.dto1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OwnerDto 
{
	String login;
	String firstName;
	String lastName;
	String numberTelephone;
	String email;
	String aboutMe;
	String photo;
}
