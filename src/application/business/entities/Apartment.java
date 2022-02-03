package application.business.entities;

import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Apartment {

	@Id
	String apartmentName;
	String type;
	@ManyToOne
	Owner owner;
	public Apartment(String apartmentName, String type, Owner owner) {
		super();
		this.apartmentName = apartmentName;
		this.type = type;
		this.owner = owner;
	}
	
	
}
