package application.business.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = {"id"})

@Entity
@Table(name = "announcements")

public class Announcement {

	@Id
	long id;
	int price;
	Address address;
	LocalDate avaliable;
	int minDurationOfStay;
	int securityDeposit;
	int cancellationTime;
	
	public Announcement(int id, int price, LocalDate avaliable, int minDurationOfStay, int securityDeposit,
			int cancellationTime) {
		super();
		this.id = id;
		this.price = price;
		this.avaliable = avaliable;
		this.minDurationOfStay = minDurationOfStay;
		this.securityDeposit = securityDeposit;
		this.cancellationTime = cancellationTime;
	}
	
}