package application.business.services.interfacesImplementation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import application.business.dto1.OwnerDto;
import application.business.dto.toFront.RenterDto;
import application.business.dto1.AnnouncementDto;
import application.business.entities.Address;
import application.business.entities.Announcement;
import application.business.entities.Owner;
import application.business.entities.RealtyObject;
import application.business.entities.RentedObject;
import application.business.entities.Renter;
import application.business.repositories.OwnerRepository;
import application.business.repositories.RenterRepository;
import application.business.services.interfaces.IRentMe;
import application.security.services.BadRequestException;

@Service
public class RentMe implements IRentMe{

	@Autowired RenterRepository userRepo;
	@Autowired OwnerRepository ownerRepo;

	@Override
	public Owner addOwner(OwnerDto owner) {
			if(ownerRepo.existsById(owner.getLogin()))
			throw new BadRequestException("Owner already exists");
		return ownerRepo.save(new Owner(owner.getLogin(), owner.getEmail(), owner.getFirstName(),  owner.getLastName(), owner.getNumberTelephone(), owner.getAboutMe(), owner.getPhoto()));
	}

	@Override
	public Owner removeOwner(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Renter addRenter(@Valid RenterDto renter) {
		if(userRepo.existsById(renter.getLogin()))
			throw new BadRequestException("Renter already exists");
		return userRepo.save(new Renter(renter.getLogin(), renter.getFirstName(), renter.getLastName(), renter.getNumberTelephone(), renter.getEmail(), renter.getPhoto()));
	}

	@Override
	public Renter removeEnter(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Announcement addAnnouncement(AnnouncementDto announcement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Announcement removeAnnouncement(String nameOfRentObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RentedObject> userHistory(String numberTelephone) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
