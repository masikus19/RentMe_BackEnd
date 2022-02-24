package application.business.dto.fromFront;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import application.business.dto.Period;
import application.business.validation.annotations.AvailableUpperRange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EditAnnouncDto {
	
		long idAnnouncement;
	
	 	@JsonInclude(Include.NON_NULL)
	    String nameOfRealtyObject;

		@JsonInclude(Include.NON_NULL)
	 	@Min(1)
	    int size;

		@JsonInclude(Include.NON_NULL)
		@Min(1) 
	    int bedrooms;
	    
	    @JsonInclude(Include.NON_NULL)
	    int bathrooms;

		@JsonInclude(Include.NON_NULL)
	    @Pattern(regexp = "^[0-9]+$")
		int price;
	    
		@JsonInclude(Include.NON_NULL)
	    @JsonFormat(pattern="dd-MM-yyyy")
	    @AvailableUpperRange(years = 3)
		LocalDate available;
	    
		@JsonInclude(Include.NON_NULL)
		Period minDurationOfStay;
	    
		@JsonInclude(Include.NON_NULL)
		Period securityDeposit;
	    
		@JsonInclude(Include.NON_NULL)
		Period cancellationTime;
		
	    @JsonInclude(Include.NON_NULL)
		Set<String> amenities;
		
}
