package application.business.dto.fromFront;

import application.business.dto1.UnitTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PeriodDto
{
	UnitTime unitTime;
	int period;	
}
