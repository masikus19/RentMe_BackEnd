package application.business.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "message")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@ManyToOne
	Announcement announcement;
	
	String message;
	@Column(name = "date_time_of_message")
	LocalDateTime dateTimeOfMessage;
	
	@ManyToOne
	Renter renter;
	@ManyToOne
	Owner owner;
	
	public Message(Announcement announcement, String message, LocalDateTime dateTimeOfMessage, Renter renter, Owner owner) {
		super();
		this.announcement = announcement;
		this.message = message;
		this.dateTimeOfMessage = dateTimeOfMessage;
		this.renter = renter;
		this.owner = owner;
	}
}
