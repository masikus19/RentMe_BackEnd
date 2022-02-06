package application.business.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = {"email"})

@Entity
@Table(name = "renter")
public class Renter 
{
	String firstName;
	String lastName;
	String numberTelephone;
	@Id
	@Column(length=100)
	String email;
	
	@OneToMany(mappedBy = "renter")
	List<RentedObject> rentHistory = new ArrayList<RentedObject>();
	
	@OneToMany(mappedBy = "renter")
	Set<RentedObject> favorites = new HashSet<RentedObject>();

	public Renter(String firstName, String lastName, String numberTelephone, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.numberTelephone = numberTelephone;
		this.email = email;
	}
	
	

}
