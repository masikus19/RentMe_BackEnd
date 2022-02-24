package application.business.dto1;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnnouncementsByFiltersDto {

	@JsonInclude(Include.NON_NULL)
	int minPrice;
	@JsonInclude(Include.NON_NULL)
	int maxPrice;
	@JsonInclude(Include.NON_NULL)
	String[] typesOfRealtyObjects;
	@JsonInclude(Include.NON_NULL)
	int[] rooms;
	@JsonInclude(Include.NON_NULL)
	LocalDate dateFrom;
	@JsonInclude(Include.NON_NULL)
	LocalDate dateTo;
	@JsonInclude(Include.NON_NULL)
	List<String> amenities;
	
	
	
	

}
