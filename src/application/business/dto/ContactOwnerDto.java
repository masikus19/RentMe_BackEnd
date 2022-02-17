package application.business.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactOwnerDto {
	String loginOfOwner;
	String loginOfRenter;
	String email;
	String message;
	Long idOfAnnouncement;
}
