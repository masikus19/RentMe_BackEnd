package application.business.dto.fromFront;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindPlaceDto 
{
	@NotNull
	String country;
	@NotNull
	String city;
	@NotNull
	int minPrice;
	@NotNull
	int maxPrice;
	@NotNull
	@Pattern(regexp = "^[0-9]+$", message = "count rooms must contain only digits")
	Integer[] rooms;

}
