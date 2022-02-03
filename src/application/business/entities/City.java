package application.business.entities;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class City {

	
	
	@Id
	String countryName;
	String cityName;
	String districtName;
	
	public City(String countryName, String cityName, String districtName) 
	{
		super();
		this.countryName=countryName;
		this.cityName = cityName;
		this.districtName = districtName;
	}
}
