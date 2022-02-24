package application.business.dto1;

import java.time.LocalDate;
import java.util.Set;

import application.business.dto.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnnouncementDto 
{
	String nameOfRentObject;
	String typeOfRealtyObject;
	int price;
	String description;
	LocalDate available;
	Period minDurationOfStay;
	int size;
	Period securityDeposit;
	int floor;
	int bedrooms;
	Period cancellationTime;
	Set<String> amenities;
}
