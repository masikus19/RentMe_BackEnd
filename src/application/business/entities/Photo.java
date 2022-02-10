package application.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = {"id"})

@Entity
@Table(name = "photo")
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String url;
	@ManyToOne
	@JoinColumn(name = "realty_object")
	RealtyObject realtyObject;
	
	public Photo(String url, RealtyObject realtyObject) {
		super();
		this.url = url;
		this.realtyObject = realtyObject;
	}
}
