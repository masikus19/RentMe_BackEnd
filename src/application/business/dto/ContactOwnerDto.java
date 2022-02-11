package application.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactOwnerDto 
{
	String nameOfRenter;
	String phoneOfRenter;
	String emailOfRenter;
	String message;
	String loginOfOwner;
}
