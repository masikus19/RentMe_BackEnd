package application.business.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	long id;
	
	@OneToOne
	@JoinColumn(name = "realty_object")
	RealtyObject realtyObject;
	int price;
	LocalDate available;
	@Column(name = "min_duration_of_stay")
	int minDurationOfStay;
	@Column(name = "security_deposit")
	int securityDeposit;
	@Column(name = "cancellation_time")
	int cancellationTime;
	
	@ManyToMany
	@JoinTable(name = "history")
	List<Renter> renterH = new ArrayList<Renter>();
	
	@ManyToMany
	@JoinTable(name = "favorite")
	Set<Renter> renterF = new HashSet<Renter>();
		
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
