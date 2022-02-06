package application.business.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = {"id"})

@Entity
@Table(name = "announcement")
public class Announcement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@OneToOne
	RealtyObject realtyObject;
	int price;
	LocalDate available;
	int minDurationOfStay;
	int securityDeposit;
	int cancellationTime;
	
	public Announcement(RealtyObject realtyObject, int price, LocalDate available, int minDurationOfStay,
			int securityDeposit, int cancellationTime) {
		super();
		this.realtyObject = realtyObject;
		this.price = price;
		this.available = available;
		this.minDurationOfStay = minDurationOfStay;
		this.securityDeposit = securityDeposit;
		this.cancellationTime = cancellationTime;
	}
	
	
	
	
	
	
}
