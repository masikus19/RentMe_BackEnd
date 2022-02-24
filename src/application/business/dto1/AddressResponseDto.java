package application.business.dto1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressResponseDto 
{
	String countryName;
	String cityName;
	String street;
	String numberOfHouse;
	double lat;
	double lng;
	
}
