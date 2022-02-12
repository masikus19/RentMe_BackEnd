package application.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Set;
import java.util.HashSet;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = {"fullAddress"})

@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@Column(name = "full_address", length = 249)
	String fullAddress;	
	
	@Column(name = "country_name")
	String countryName;
	@Column(name = "city_name")
	String cityName;
	String street;
	@Column(name = "number_of_house")
	String numberOfHouse;
	double lat;
	double lng;
	
	@OneToMany(mappedBy = "address")
	Set<RealtyObject> realtyObjects = new HashSet<RealtyObject>();
	
	public Address(String fullAddress, String countryName, String cityName, String street, String numberOfHouse, double lat, double lng) {
		super();
		this.fullAddress = fullAddress;
		this.countryName = countryName;
		this.cityName = cityName;
		this.street = street;
		this.numberOfHouse = numberOfHouse;
		this.lat = lat;
		this.lng = lng;
	}
}
