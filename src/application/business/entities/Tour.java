package application.business.entities;

import java.time.LocalDate;

import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class Tour {

	LocalDate from;
    LocalDate to;
    @Id
    int idOfAnnouncement;
    
	public Tour(LocalDate from, LocalDate to, int idOfAnnouncement) {
		super();
		this.from = from;
		this.to = to;
		this.idOfAnnouncement = idOfAnnouncement;
	}
    
    
    
}
