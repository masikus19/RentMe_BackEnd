package application.business.dto.fromFront;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import application.business.validation.annotations.AvailableUpperRange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EditAnnouncDto {
	
		long idAnnouncement;

	    String nameOfRealtyObject;

	 	@Min(1)
	    int size;

		@Min(1) 
	    int bedrooms;
	   
	    int bathrooms;

	    @Pattern(regexp = "^[0-9]+$")
		int price;
	    
	    @JsonFormat(pattern="dd-MM-yyyy")
	    @AvailableUpperRange(years = 3)
		LocalDate available;

		PeriodDto minDurationOfStay;

		PeriodDto securityDeposit;

		PeriodDto cancellationTime;

		Set<String> amenities;
		
}
