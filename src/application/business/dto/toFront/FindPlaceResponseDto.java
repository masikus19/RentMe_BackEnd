package application.business.dto.toFront;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class FindPlaceResponseDto {
	
	long idAnnouncement;
	String photo;
	int square;
	int bedrooms;
	int bathrooms;
	int price;
	double lat;
	double lng;

}
