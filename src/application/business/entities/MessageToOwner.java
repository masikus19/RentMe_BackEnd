package application.business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "message")
public class MessageToOwner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@Column(name = "renter_name")
	String nameOfRenter;
	@Column(name = "phone_renter")
	String phoneOfRenter;
	@Column(name = "email_renter")
	String emailOfRenter;
	String message;
	@ManyToOne
	Owner owner;
	
	public MessageToOwner(String nameOfRenter, String phoneOfRenter, String emailOfRenter, String message,
			Owner owner) {
		super();
		this.nameOfRenter = nameOfRenter;
		this.phoneOfRenter = phoneOfRenter;
		this.emailOfRenter = emailOfRenter;
		this.message = message;
		this.owner = owner;
	}
	
	

}
