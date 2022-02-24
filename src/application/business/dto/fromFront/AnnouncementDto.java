package application.business.dto.fromFront;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import application.business.dto.Period;
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
    @Pattern(regexp = "^[0-9]+$")
	int price;
    
    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    @AvailableUpperRange(years = 3)
	LocalDate available;
    
    @NotNull
	Period minDurationOfStay;
    
    @NotNull
	Period securityDeposit;
    
    @NotNull
	Period cancellationTime;
	
//	String loginOwner;

//    @Size(min = 5, max = 50)
//    @Pattern(regexp = "^[A-Za-z]+$")
//    String countryName;
//
//    @Size(min = 2, max = 50)
//    @Pattern(regexp = "^[A-Za-z]+$")
//    String cityName;
//
//    @Size(min = 3, max = 50)
//    @Pattern(regexp = "^[A-Za-z0-9'`]+$")
//    String streetName;
//
//    @NotNull
//    @Min(1) 
//    int numberOfHouse;

//    @NotNull
//    String nameOfRealtyObject;
//
//    @NotNull @Min(1)
//    int size;

//    @NotNull @Min(1)
//    int floor;
//
//    @NotNull @Min(1) 
//    int bedrooms;
//    
//    @JsonInclude(Include.NON_NULL)
//    int bathrooms;

//    @JsonInclude(Include.NON_NULL)
//    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Appt number name is not valid")
//    @Size(min = 1, max = 500)
//    String apptNumber;
//
//    @JsonInclude(Include.NON_NULL)
//    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Block number name is not valid")
//    @Size(min = 1, max = 500)
//    String blockNumber;
	

//	
//    @JsonInclude(Include.NON_NULL)
//	Set<String> amenities;
	

}
