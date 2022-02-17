package application.business.entities;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;
@NoArgsConstructor
@Setter
@Getter
public class Owner {

	
	String firstName;
	String lastName;
	String phone;
	@Id
	String email;
	
	@OneToMany(mappedBy="owner")
	List<Apartment> otherApatments = new ArrayList<>();
	
	public Owner(String firstName, String lastName, String phone, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		
	}
	
	
	
	
	
	
}
