package application.business.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageToOwnerDto 
{
	String loginOfRenter;
	String loginOfOwner;
	String message;
	LocalDateTime dateTimeOfMessage;
	Long idOfAnnouncement;
	
	
	
}
