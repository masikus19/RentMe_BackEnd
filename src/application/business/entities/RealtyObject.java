package application.business.entities;

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
@Table(name = "realty_object")
public class RealtyObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@Column(name = "name_of_realty_object")
	String nameOfRealtyObject;
	@ManyToOne
	Address address;
	@ManyToOne
	@JoinColumn(name = "type_of_realty_object")
	TypeOfRealtyObject typeOfRealtyObject;
	@ManyToOne
	Owner owner;
	int size;
	int floor;
	int bedrooms;
	int bathrooms;
	String appt;
	String block;
	boolean rented;
	
	@OneToOne
	Announcement announcement;
	
	@OneToMany(mappedBy = "realtyObject")
	List<Photo> photos = new ArrayList<Photo>();
	
	@ManyToMany(mappedBy = "realtyObject")
	Set<Amenitie> amenitie = new HashSet<Amenitie>();

	public RealtyObject(String nameOfRentObject, Address address, TypeOfRealtyObject typeOfRealtyObject, Owner owner,
			int size, int floor, int bedrooms, int bathrooms, String appt, String block, Announcement announcement) {
		super();
		this.nameOfRealtyObject = nameOfRentObject;
		this.address = address;
		this.typeOfRealtyObject = typeOfRealtyObject;
		this.owner = owner;
		this.size = size;
		this.floor = floor;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.appt = appt;
		this.block = block;
		this.announcement = announcement;
	}

	
}
