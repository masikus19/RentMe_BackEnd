package application.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindPlaceDto 
{
	String country;
	String city;
	int minPrice;
	int maxPrice;
	Integer[] rooms;

}
