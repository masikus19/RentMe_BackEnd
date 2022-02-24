package application.business.dto.fromFront;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestTourDto 
{
	String loginOfRenter;
	String loginOfOwner;
	long idOfAnnouncement;
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	LocalDate rentFrom;
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	LocalDate rentTo;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime dateTimeOfMessage;
}
