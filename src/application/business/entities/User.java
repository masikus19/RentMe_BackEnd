package application.business.entities;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.OneToMany;

import org.springframework.data.annotation.Id;

import application.business.service.GuestService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
public class User {
	
	
	String firstName;
	String lastName;
	String phone;
	@Id
	String email;
	
	@OneToMany(mappedBy = "user")
	List<Apartment> rentHistory = new ArrayList<>();
	
	public  User(GuestService guest) {
		super();
		firstName = guest.getFirstName();
		lastName = guest.getLastName();
		phone = guest.getPhone();
		email = guest.getEmail();
	}
	
	

}
