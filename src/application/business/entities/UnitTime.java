package application.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter

@Entity
@Table(name="unit_time")
public class UnitTime {
	
	@Id
	@Column(name="unit_time")
	String unitTime;

	public UnitTime(String unitTime) {
		super();
		this.unitTime = unitTime;
	}
	
	

}
