package application.business.dto.fromFront;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import application.business.validation.annotations.AvailableUpperRange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnnouncementDto {
	
	long idOfRealtyObject;
	
    @NotNull
	int price;
    
    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    @AvailableUpperRange(years = 3)
	LocalDate available;
    
    @NotNull
	int minDurationOfStay;
    
    @NotNull
	int securityDeposit;
    
    @NotNull
	int cancellationTime;
}
