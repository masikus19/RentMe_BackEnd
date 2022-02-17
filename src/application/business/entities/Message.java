package application.business.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class Message {
    @Id
	long id;
    @Column(length=150)
	String text;
	@ManyToOne
    Owner owner;
    @ManyToOne
    Renter renter;
    @ManyToOne
    Announcement announcement;
    
	public Message(Renter renter, Owner owner, Announcement announcement, String text) {
		
		this.renter = renter;
		this.owner = owner;
		this.announcement = announcement;
		this.text = text;
	}
    
    
    
}
