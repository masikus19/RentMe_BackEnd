package application.business.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.business.api.BusinessResponces;
import application.business.dto.ContactOwnerDto;
import application.business.dto.EditProfileDto;
import application.business.dto.FindPlaceDto;
import application.business.entities.Announcement;
import application.business.entities.Message;
import application.business.entities.Owner;
import application.business.entities.Renter;
import application.business.entities.Tour;
import application.business.repo.AddressRepository;
import application.business.repo.AnnouncementRepo;
import application.business.repo.MessageRepo;
import application.business.repo.OwnerRepo;
import application.business.repo.RenterRepo;
import application.business.repo.TourRepo;
import application.security.services.BadRequestException;


@Service
public class RenterService implements IRenterService {

	@Autowired AddressRepository addressRepo;
	@Autowired AnnouncementRepo announcementRepo;
	@Autowired RenterRepo renterRepo;
	@Autowired TourRepo  tourRepo;
	@Autowired OwnerRepo  ownerRepo;
	@Autowired MessageRepo messageRepo;
	
	@Override
	public Renter getProfile(String loginOfRenter) {
		
		if(loginOfRenter == null || loginOfRenter.isEmpty())
			throw new BadRequestException(BusinessResponces.LOGIN_NOT_FOUND);
		
		Renter renter = renterRepo.findById(loginOfRenter).orElse(null);
		
		if(renter == null)     throw new BadRequestException(BusinessResponces.RENTER_NOT_FOUND);
		return renter;
	}

	@Override
	public Renter editProfile(String loginOfRenter, EditProfileDto editRenter) {
		
		if(editRenter == null) throw new BadRequestException("Not found");   
		
		Renter renter = getProfile(loginOfRenter);
		
	    renter.setFirstName(editRenter.getFirstName());
	    renter.setLastName(editRenter.getLastName());
	    renter.setPhone(editRenter.getNumberTelephone());
	    renter.setEmail(editRenter.getEmail());
	     
	    renterRepo.save(renter);
	    
		return renter;
	}

	@Override
	public List<Announcement> findPlace(FindPlaceDto findPlace) {
		
		List<Announcement> announcmentList = new ArrayList<Announcement>();
	
		for (Announcement announcement : announcementRepo.findAll()) {
			
			if(announcement.getAddress() == null) continue;
			
			if(announcement.getAddress().getCityName() == findPlace.getCity() &&
			   announcement.getAddress().getCountryName() == findPlace.getCountry() &&
			   announcement.getAddress().getStreetName() == findPlace.getStreet() &&
			   announcement.getAddress().getNumberOfApartment() == findPlace.getNumber()
					)
			{
				announcmentList.add(announcement);
			}
		}
		
		return announcmentList ;
	}

	@Override
	public List<Announcement> getFavorites(String loginOfRenter) {
		
		Renter renter = getProfile(loginOfRenter);
		
		return renter.getFavorites();
	}

	

	@Override
	public List<Announcement> addFavorite( String loginOfRenter ,long idOfAnnoncement) {
		
		Renter renter = getProfile(loginOfRenter);
		Announcement annoucnment = getAnnoucement(idOfAnnoncement);		
		List<Announcement> fav = renter.getFavorites();
		
		if(fav == null) throw new BadRequestException(BusinessResponces.LIST_IS_NULL);
		
	    fav.add(annoucnment);
	    
		return fav;
	}

	private Announcement getAnnoucement(long idOfAnnouncement) {
		Announcement annoucnment = announcementRepo.findById(idOfAnnouncement).orElse(null);
		
		if(annoucnment == null)     throw new BadRequestException(BusinessResponces.ADDRESS_NOT_FOUND);
		return annoucnment;
	}

	@Override
	public List<Announcement> removeFavorite(String loginOfRenter, long idOfAnnouncement) {
		
		Renter renter = getProfile(loginOfRenter);
		Announcement annoucnment = getAnnoucement(idOfAnnouncement);
		List<Announcement> fav = renter.getFavorites();
		
		if(fav == null) throw new BadRequestException(BusinessResponces.LIST_IS_NULL);
		
	    fav.remove(annoucnment);
	    
	    return fav;
	}

	@Override 
	public Set<Announcement> getHistory(String loginOfRenter) {
		Renter renter = getProfile(loginOfRenter);
		
		return renter.getHistory();
	}

	@Override
	public Set<Announcement> addAnnouncementToHistory(String loginOfRenter, long idOfAnnouncement) {
		
		Renter renter = getProfile(loginOfRenter);
		Announcement annoucment = getAnnoucement(idOfAnnouncement);
		Set<Announcement> his = renter.getHistory();
		
		if(his == null) throw new BadRequestException(BusinessResponces.SET_IS_NULL);
		
		his.add(annoucment);
		
		return his;
	}

	@Override
	public List<Tour> requestTour(LocalDate from, LocalDate to, long idOfAnnouncement) {
		
		List<Tour> tours = tourRepo.findTour(from, to, idOfAnnouncement);
		
		if(tours == null) throw new BadRequestException(BusinessResponces.TOUR_NOT_FOUND);
		
		return tours; 
	}

	@Override
	public Owner applyToOwner(String loginOfOwner) {
		
		if(loginOfOwner == null || loginOfOwner.isEmpty())
			throw new BadRequestException(BusinessResponces.OWNER_NOT_FOUND);
		
		return ownerRepo.findById(loginOfOwner).orElse(null);
	}

	@Override
	public String contactOwner(ContactOwnerDto contactOwner) {
		
		Announcement announcement = announcementRepo.findById(contactOwner.getIdOfAnnouncement()).orElse(null);
		if(announcement == null) throw new BadRequestException(BusinessResponces.ANNOUNCEMENT_NOT_FOUND);
		
		Owner owner = ownerRepo.findById(contactOwner.getLoginOfOwner()).orElse(null);
		if(owner == null) throw new BadRequestException(BusinessResponces.OWNER_NOT_FOUND);
		
		Renter renter = renterRepo.findById(contactOwner.getLoginOfRenter()).orElse(null);
		if(renter == null) throw new BadRequestException(BusinessResponces.RENTER_NOT_FOUND);
		
		messageRepo.save(new Message(renter, owner, announcement, contactOwner.getMessage()));
		
		return "Message was send to owner";
	}

	
	
	
	
	
}
