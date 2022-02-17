package application.business.services;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static application.business.dto.BusinessMapper.*;
import application.business.dto.*;

import application.business.entities.Announcement;
import application.business.entities.Message;
import application.business.entities.Owner;
import application.business.entities.RealtyObject;
import application.business.entities.Renter;
import application.business.repositories.AddressRepository;
import application.business.repositories.AnnouncementRepository;
import application.business.repositories.MessageRepository;
import application.business.repositories.OwnerRepository;
import application.business.repositories.RealtyObjectRepository;
import application.business.repositories.RenterRepository;

@Service
public class RenterService implements IRenter
{
	@Autowired RenterRepository renterRepo;
	@Autowired AnnouncementRepository announcRepo;
	@Autowired ModelMapper mapper;
	@Autowired RealtyObjectRepository objectRepo;
	@Autowired AddressRepository addressRepo;
	@Autowired MessageRepository messageRepo;
	@Autowired OwnerRepository ownerRepo;

	@Override
	public RenterDto getProfile(String loginOfRenter) 
	{
		Renter renter = renterRepo.findById(loginOfRenter).orElse(null);	
		return mapper.map(renter, RenterDto.class);
	}

	@Override
	public synchronized RenterDto editProfile(String login, EditProfileDto data) {
		Renter renter = renterRepo.findById(login).orElse(null);	
		if(data.getFirstName()!=null)
			renter.setFirstName(data.getFirstName());
		if(data.getLastName()!=null)
			renter.setLastName(data.getLastName());
		if(data.getEmail()!=null)
			renter.setEmail(data.getEmail());
		if(data.getNumberTelephone()!=null)
			renter.setNumberTelephone(data.getNumberTelephone());
		if(data.getPhoto()!=null)
			renter.setPhoto(data.getPhoto());
		
		return mapper.map(renterRepo.save(renter),  RenterDto.class);
	}

	@Override
	public List<FindPlaceResponseDto> findPlace(FindPlaceDto data) {

		List<RealtyObject> realtyObjects = objectRepo.findByCountryAndCity(data.getCountry(), data.getCity());
		
		List<FindPlaceResponseDto> res = realtyObjects.stream().filter(o -> isCorrectRooms(o.getBedrooms(), data.getRooms()))
		.flatMap(o -> o.getAnnouncements().stream().filter(a -> isCorrectPrice(a.getPrice(), data.getMinPrice(), data.getMaxPrice())))
		.map(BusinessMapper::toFindPlaceResponseDto).collect(Collectors.toList());
		
		return res;
	}
	
	private boolean isCorrectPrice(int price, int minPrice, int maxPrice) 
	{
		return price>=minPrice && price<=maxPrice;
	}

	private boolean isCorrectRooms(int bedrooms, Integer[] rooms)
	{
		List<Integer> room = Arrays.asList(rooms);
		return room.contains(bedrooms);
	}

	@Override
	public Set<FindPlaceResponseDto> getFavorites(String loginOfRenter) 
	{
		Renter renter = renterRepo.findById(loginOfRenter).orElse(null);
		Set<Announcement> favorites = renter.getAnnouncementF();
		return toSetFindPlaceResponseDto(favorites);
	}

	@Override
	public synchronized String addFavorite(String loginOfRenter, long idOfAnnoncement) 
	{
		Renter renter = renterRepo.findById(loginOfRenter).orElse(null);
		Set<Announcement> favorites = renter.getAnnouncementF();
		Announcement announc = announcRepo.findById(idOfAnnoncement).orElse(null);
		favorites.add(announc);
		return "Announcement added to favorites";
	}

	@Override
	public synchronized String removeFavorite(String loginOfRenter, long idOfAnnoncement) 
	{
		Renter renter = renterRepo.findById(loginOfRenter).orElse(null);
		Set<Announcement> favorites = renter.getAnnouncementF();
		Announcement announc = announcRepo.findById(idOfAnnoncement).orElse(null);
		favorites.remove(announc);
		return "Announcement removed from favorites";
	}

	@Override
	public Set<FindPlaceResponseDto> getHistory(String loginOfRenter) 
	{
		Renter renter = renterRepo.findById(loginOfRenter).orElse(null);
		Set<Announcement> history = renter.getAnnouncementH();
		return toSetFindPlaceResponseDto(history);
	}

	@Override
	public synchronized String addAnnouncementToHistory(String loginOfRenter, long idOfAnnoncement) {
		//TODO 10 announc in history
		Renter renter = renterRepo.findById(loginOfRenter).orElse(null);
		Set<Announcement> history = renter.getAnnouncementH();
		Announcement announc = announcRepo.findById(idOfAnnoncement).orElse(null);
		history.add(announc);
		return  "Announcement added to history";
	}

	@Override
	//TODO
	public String requestTour(RequestTourDto data) {
		Announcement announc = announcRepo.findById(data.getIdOfAnnouncement()).orElse(null);
//		Owner owner = ownerRepo.findById(data.getLoginOfOwner()).orElse(null);
//		Renter renter = renterRepo.findById(data.getLoginOfRenter()).orElse(null);
		
		String message = 
				String.format("I want to rent %s from %tF to %tF", announc.getRealtyObject().getNameOfRealtyObject(), data.getRentFrom(), data.getRentTo());
		
//		messageRepo.save(new Message(announc, message, data.getDateTimeOfMessage(), renter, owner));
//		return "Message sent to owner";
		
		MessageToOwnerDto messageToOwner = 
				new MessageToOwnerDto(data.getLoginOfRenter(), data.getLoginOfOwner(), message, data.getDateTimeOfMessage(), data.getIdOfAnnouncement());
		return contactOwner(messageToOwner);
	}

	@Override
	public OwnerDto applyToOwner(String loginOfOwner) {
		Owner owner = ownerRepo.findById(loginOfOwner).orElse(null);
		return mapper.map(owner, OwnerDto.class);
	}

	@Override
	public String contactOwner(MessageToOwnerDto data) {
		Announcement announc = announcRepo.findById(data.getIdOfAnnouncement()).orElse(null);
		Owner owner = ownerRepo.findById(data.getLoginOfOwner()).orElse(null);
		Renter renter = renterRepo.findById(data.getLoginOfRenter()).orElse(null);
		messageRepo.save(new Message(announc, data.getMessage(), data.getDateTimeOfMessage(), renter, owner));
		return "Message sent to owner";
	}

}
