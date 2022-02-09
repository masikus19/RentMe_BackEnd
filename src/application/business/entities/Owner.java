package application.business.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.*;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = {"login"})

@Entity
@Table(name = "owner")
public class Owner {
	
	@Id
	@Column(length=100)
	String login;
	
	@Email
	String email;
	String firstName;
	String lastName;
	String numberTelephone;
	String aboutMe;
	
	@OneToMany(mappedBy = "owner")
	List<RealtyObject> realtyObjects = new ArrayList<>();
	
	@OneToMany(mappedBy = "owner")
	List<MessageToOwner> messages = new ArrayList<>();
	

	public Owner(String login, @Email String email, String firstName, String lastName, String numberTelephone,
			String aboutMe) {
		super();
		this.login = login;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.numberTelephone = numberTelephone;
		this.aboutMe = aboutMe;
	}
	
	
}
