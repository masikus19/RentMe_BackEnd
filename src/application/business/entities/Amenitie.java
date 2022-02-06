package application.business.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(exclude = "realtyObjects")

@Entity
@Table(name = "amenitie")
public class Amenitie
{
	@Id
	@Column(length=100)
	String name;
	
	@ManyToMany
	Set<RealtyObject> realtyObjects = new HashSet<RealtyObject>();

	public Amenitie(String name) {
		super();
		this.name = name;
	}
	
	
}
