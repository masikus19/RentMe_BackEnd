package application.business.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	String email;
	@Column(name = "first_name")
	String firstName;
	@Column(name = "last_name")
	String lastName;
	@Column(name = "number_telephone")
	String numberTelephone;
	@Column(name = "about_me")
	String aboutMe;
	String photo;
	
	@OneToMany(mappedBy = "owner")
	List<RealtyObject> realtyObjects = new ArrayList<>();
	
	@OneToMany(mappedBy = "owner")
	List<Message> messages = new ArrayList<>();
	

	public Owner(String login,String email, String firstName, String lastName, String numberTelephone,
			String aboutMe, String photo) {
		super();
		this.login = login;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.numberTelephone = numberTelephone;
		this.aboutMe = aboutMe;
		this.photo = photo;
	}
	
	
}
