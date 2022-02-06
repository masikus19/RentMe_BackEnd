package application.business.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = {"id"})

@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String countryName;
	String cityName;
	String street;
	String numberOfHouse;
	double lat;
	double lng;
	
	public Address(String countryName, String cityName, String street, String numberOfHouse, double lat, double lng) {
		super();
		this.countryName = countryName;
		this.cityName = cityName;
		this.street = street;
		this.numberOfHouse = numberOfHouse;
		this.lat = lat;
		this.lng = lng;
	}
	
	
	
	
}
