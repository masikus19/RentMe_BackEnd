package application.business.services.interfaces;

import java.util.List;

import application.business.dto.toFront.RenterDto;
import application.business.dto1.*;
import application.business.dto1.AnnouncementDto;
import application.business.entities.Address;
import application.business.entities.Announcement;
import application.business.entities.Owner;
import application.business.entities.RealtyObject;
import application.business.entities.RentedObject;
import application.business.entities.Renter;

public interface IRentMe {
	Owner addOwner(OwnerDto owner);
	Owner removeOwner(String email);
	Renter addRenter(RenterDto user);
	Renter removeEnter(String email);
	Announcement addAnnouncement(AnnouncementDto announcement);
	Announcement removeAnnouncement(String nameOfRentObject);
	List<RentedObject> userHistory(String numberTelephone);
	
}
