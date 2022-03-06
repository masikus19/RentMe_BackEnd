package application.business.dto1;

import java.time.LocalDate;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentDataDto 
{
	String cityName;
	int price;
	String typeOfRentObject;
	int rooms;
	LocalDate rentFrom;
	HashSet<String> amenities;
	
}
