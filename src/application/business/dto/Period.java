package application.business.dto;

import application.business.dto1.UnitTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Period
{
	UnitTime unitTime;
	int period;
	
}
