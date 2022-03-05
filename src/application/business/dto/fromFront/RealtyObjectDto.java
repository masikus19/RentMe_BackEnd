package application.business.dto.fromFront;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import static com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Set;

@NoArgsConstructor
@Getter @Setter
public class RealtyObjectDto {

    String loginOwner;

    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[A-Za-z]+$")
    String countryName;

    @Size(min = 2, max = 50)
//    @Pattern(regexp = "^[A-Za-z]+$")
    String cityName;

    @Size(min = 3, max = 50)
//    @Pattern(regexp = "^[A-Za-z0-9'`]+$")
    String streetName;

    @NotNull
    @Min(1) 
    int numberOfHouse;

    @NotNull
    String nameOfRealtyObject;
    
    @NotNull
    String typeOfRealtyObject;

    @NotNull @Min(1)
    int size;

    @NotNull @Min(1)
    int floor;

    @NotNull @Min(1) 
    int bedrooms;
    
    int bathrooms;

    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Appt number name is not valid")
    @Size(min = 1, max = 500)
    String apptNumber;

    @JsonInclude(Include.NON_NULL)
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Block number name is not valid")
    String blockNumber;
    
    @NotNull
    String description;

   	Set<String> amenities;
    
    Set<String> photos;
}
