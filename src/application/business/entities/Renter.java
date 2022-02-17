package application.business.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import application.business.service.GuestService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
public class Renter {
	
	@Id
	@Column(length=20)
	String login;
	String firstName;
	String lastName;
	String phone;
	String email;
	
	@ManyToMany(mappedBy = "renterFav")
	List<Announcement> favorites = new ArrayList<>();
	@ManyToMany(mappedBy = "renterHis")
	Set<Announcement> history = new HashSet<>();
	
	
	public  Renter(GuestService guest) {
		login = guest.getLogin();
		firstName = guest.getFirstName();
		lastName = guest.getLastName();
		phone = guest.getPhone();
		email = guest.getEmail();
	}
	
	
	
	public Renter(String login) {
        this.login = login;
    }



	

}
