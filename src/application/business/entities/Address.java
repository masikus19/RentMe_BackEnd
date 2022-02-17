package application.business.entities;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Address {

	@Id
	@Column(length=100)
	String fullAddress;
	@Column(length=100)
	String countryName;
	String cityName;
	@Column(length=100)
	String streetName;
	int numberOfApartment;
	
	public Address(String fullAddress, String countryName, String cityName, String streetName, int numberOfApartment) {
		super();
		this.fullAddress = fullAddress;
		this.countryName = countryName;
		this.cityName = cityName;
		this.streetName = streetName;
		this.numberOfApartment = numberOfApartment;
	}
	
	
	
	
	
}
