package application.business.entities;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class Country {

	
	
	@Id
	String countryName;
	String cityName;
	String districtName;
	
	public Country(String countryName, String cityName, String districtName) 
	{
		super();
		this.countryName=countryName;
		this.cityName = cityName;
		this.districtName = districtName;
	}
}
