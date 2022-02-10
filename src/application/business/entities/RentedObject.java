package application.business.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "rentedObject")
public class RentedObject 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@OneToOne
	@JoinColumn(name = "realty_object")
	RealtyObject realtyObject;
	
	@ManyToOne
	Renter renter;
	@Column(name = "rented_from")
	LocalDate rentedFrom;
	
	public RentedObject(RealtyObject realtyObject, Renter renter, LocalDate rentedFrom) {
		super();
		this.realtyObject = realtyObject;
		this.renter = renter;
		this.rentedFrom = rentedFrom;
	}
	
	
}
