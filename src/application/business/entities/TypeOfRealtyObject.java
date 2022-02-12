package application.business.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = {"name"})

@Entity
@Table(name = "type_of_realty_object")
public class TypeOfRealtyObject {
	@Id
	@Column(length=100)
	String name;
	
	@OneToMany(mappedBy = "typeOfRealtyObject")
	Set<RealtyObject> realtyObjects = new HashSet<RealtyObject>();

	public TypeOfRealtyObject(String name) {
		super();
		this.name = name;
	}
	
}
