package application.business.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "realtyObject")
public class RealtyObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String nameOfRentObject;
	@OneToOne
	Address address;
	@ManyToOne
	TypeOfRealtyObject typeOfRealtyObject;
	@ManyToOne
	Owner owner;
	int size;
	int floor;
	int bedrooms;
	
	@OneToMany(mappedBy = "")
	List<Photo> photos = new ArrayList<Photo>();
	
	boolean rented;
	
	@ManyToMany(mappedBy = "realtyObjects")
	Set<Amenitie> amenities = new HashSet<Amenitie>();

	public RealtyObject(String nameOfRentObject, Address address, TypeOfRealtyObject typeOfRealtyObject, Owner owner,
			int size, int floor, int bedrooms, boolean rented) {
		super();
		this.nameOfRentObject = nameOfRentObject;
		this.address = address;
		this.typeOfRealtyObject = typeOfRealtyObject;
		this.owner = owner;
		this.size = size;
		this.floor = floor;
		this.bedrooms = bedrooms;
		this.rented = rented;
	}

	
}
