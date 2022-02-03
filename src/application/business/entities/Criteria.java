package application.business.entities;

import java.time.LocalDate;

import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class Criteria {

	@Id
	@ManyToOne
	String apartmentName;
	@ManyToOne
	String type;
	@ManyToOne
	String countryName;
	@ManyToOne
	String cityName;
	@ManyToOne
	String districtName;
	int priceFrom;
	int priceTo;
	LocalDate rentDate;
	int amountOfRooms;
	int floor;
	int size;
	boolean elevator;
	boolean airConditioner;
	// if true
	int amountOfConditioners;
	boolean furniture;
	boolean houseware;
	boolean balckony;
	boolean busStop;
	boolean parking;
	boolean renovation;
	boolean withBroker;
	
	public Criteria(String apartmentName, String type, String countryName, String cityName, String districtName,
			int priceFrom, int priceTo, LocalDate rentDate, int amountOfRooms, int floor, int size, boolean elevator,
			boolean airConditioner, int amountOfConditioners, boolean furniture, boolean houseware, boolean balckony,
			boolean busStop, boolean parking, boolean renovation, boolean withBroker) {
		super();
		this.apartmentName = apartmentName;
		this.type = type;
		this.countryName = countryName;
		this.cityName = cityName;
		this.districtName = districtName;
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
		this.rentDate = rentDate;
		this.amountOfRooms = amountOfRooms;
		this.floor = floor;
		this.size = size;
		this.elevator = elevator;
		this.airConditioner = airConditioner;
		this.amountOfConditioners = amountOfConditioners;
		this.furniture = furniture;
		this.houseware = houseware;
		this.balckony = balckony;
		this.busStop = busStop;
		this.parking = parking;
		this.renovation = renovation;
		this.withBroker = withBroker;
	}
	
	
}
