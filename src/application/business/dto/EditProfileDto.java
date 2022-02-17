package application.business.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Valid
public class EditProfileDto 
{
	@JsonInclude(Include.NON_NULL)
	String firstName;
	@JsonInclude(Include.NON_NULL)
	String lastName;
	@JsonInclude(Include.NON_NULL)
	String numberTelephone;
	@Email
	@JsonInclude(Include.NON_NULL)
	String email;
	@JsonInclude(Include.NON_NULL)
	String photo;
}
