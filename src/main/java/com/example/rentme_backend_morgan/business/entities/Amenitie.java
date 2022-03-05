package com.example.rentme_backend_morgan.business.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
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
