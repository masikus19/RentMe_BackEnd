package application.business.entities;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = {"login"})

@Entity
@Table(name = "renter")
public class Renter 
{
	@Id
	@Column(length=100)
	String login;
	@Column(name = "first_name")
	String firstName;
	@Column(name = "last_name")
	String lastName;
	@Column(name = "number_telephone")
	String numberTelephone;
	
	String email;
	String photo;
	
	@ManyToMany(mappedBy = "renterH")
	Set<Announcement> announcementH = new LinkedHashSet<Announcement>();
	
	@ManyToMany(mappedBy = "renterF")
	Set<Announcement> announcementF = new LinkedHashSet<Announcement>();
	
	@OneToMany(mappedBy = "renter")
	List<Message> messages = new ArrayList<>();

	public Renter(String login, String firstName, String lastName, String numberTelephone, String email, String photo) {
		super();
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.numberTelephone = numberTelephone;
		this.email = email;
		this.photo = photo;
	}
}
