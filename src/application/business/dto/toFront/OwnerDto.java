package application.business.dto.toFront;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OwnerDto 
{
	String firstName;
	String lastName;
	String numberTelephone;
	String email;
	String aboutMe;
	
}
