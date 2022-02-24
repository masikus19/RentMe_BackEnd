package application.business.dto.fromFront;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageDto 
{
	String loginOfRenter;
	String loginOfOwner;
	@NotNull
	String message;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime dateTimeOfMessage;
	Long idOfAnnouncement;
	
	
	
}
