package application.business.services.interfacesImplementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.business.dto.fromFront.AnnouncementDto;
import application.business.dto.fromFront.EditAnnouncDto;
import application.business.dto.fromFront.EditProfileDto;
import application.business.dto.fromFront.RealtyObjectDto;
import application.business.dto.google.Candidates;
import application.business.dto.google.Location;
import application.business.dto.toFront.OwnerDto;
import application.business.entities.Address;
import application.business.entities.Amenitie;
import application.business.entities.Owner;
import application.business.entities.Photo;
import application.business.entities.RealtyObject;
import application.business.entities.TypeOfRealtyObject;
import application.business.repositories.AddressRepository;
import application.business.repositories.AmenitieRepository;
import application.business.repositories.OwnerRepository;
import application.business.repositories.PhotoRepository;
import application.business.repositories.RealtyObjectRepository;
import application.business.repositories.TypeOfRealtyObjectRepository;
import application.business.services.interfaces.IOwner;
import application.security.exceptionsHandling.AccountChecks;
import static application.business.services.google.ConfigRestForGoogle.createCandidates;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import static application.business.exceptionsHandling.BussinesChecks.*;

@Service
public class OwnerService implements IOwner{
	
	@Autowired OwnerRepository ownerRepo;
	@Autowired ModelMapper mapper;
	@Autowired AddressRepository addressRepo;
	@Autowired RealtyObjectRepository realtyObjectRepo;
	@Autowired TypeOfRealtyObjectRepository typeObjectRepo;
	@Autowired AmenitieRepository amenitieRepo;
	@Autowired PhotoRepository photoRepo;
	@Override
	public OwnerDto getProfile(String loginOfOwner) {
		Owner owner = ownerRepo.findById(loginOfOwner).orElse(null);
		return mapper.map(owner, OwnerDto.class);
	}

	@Override
	public synchronized OwnerDto editProfile(String login, EditProfileDto data) {
		AccountChecks.checkLoginAuth(login);
		Owner owner = ownerRepo.findById(login).orElse(null);
		if(data.getFirstName()!=null)
			owner.setFirstName(data.getFirstName());
		if(data.getLastName()!=null)
			owner.setLastName(data.getLastName());
		if(data.getEmail()!=null)
			owner.setEmail(data.getEmail());
		if(data.getNumberTelephone()!=null)
			owner.setNumberTelephone(data.getNumberTelephone());
		if(data.getPhoto()!=null)
			owner.setPhoto(data.getPhoto());
		if(data.getAboutMe()!=null)
			owner.setAboutMe(data.getAboutMe());
		return mapper.map(ownerRepo.save(owner), OwnerDto.class);
	}
	

	@Override
	@Transactional
	//TODO
	public synchronized String addRealtyObject(RealtyObjectDto data) {
		AccountChecks.checkLoginAuth(data.getLoginOwner());
		checkIsRealtyObjectExists(data);
		
		Candidates candidates = createCandidates(data);
        String formatted_address = candidates.formatted_address;
        Location location = candidates.geometry.location;

        Address address = checkIsAddressExist(formatted_address);
		
        if(address==null)
        {
        	address = saveAddress(location, formatted_address, data);	
        	saveRealtyObject(data, address);
        }
        else
        {
        	saveRealtyObject(data, address);
        }
		return "Realty object and address added to DB";
	}


	private Address saveAddress(Location location, String formatted_address, RealtyObjectDto data) 
	{
		Address address = new Address(formatted_address, data.getCountryName(), data.getCityName(), 
    			data.getStreetName(), data.getNumberOfHouse(), location.getLat(), location.getLng());
		return addressRepo.save(address);	
	}


	private void saveRealtyObject(RealtyObjectDto data, Address address) {
		Owner owner = ownerRepo.findById(data.getLoginOwner()).orElse(null);
		TypeOfRealtyObject typeOfObject = saveTypeOfObject(data.getTypeOfRealtyObject());
		
		RealtyObject object = new RealtyObject(data.getNameOfRealtyObject(), address, typeOfObject, owner, 
				data.getSize(), data.getFloor(), data.getBedrooms(), data.getBathrooms(), data.getApptNumber(), data.getBlockNumber(),
				data.getDescription());
		realtyObjectRepo.save(object);
		
		addAmenities(data.getAmenities(), object);
		addPhotos(data.getPhotos(), object);	
	}

	private void addPhotos(String[] photos, RealtyObject object) {
		Arrays.asList(photos).stream()
		.filter(f -> isNotExistsInDB(f))
		.map(f -> photoRepo.save(new Photo(f, object))).collect(Collectors.toSet());
		object.getPhotos().addAll(Arrays.asList(photos).stream().map(f->new Photo(f, object)).collect(Collectors.toSet()));
}

	private void addAmenities(String[] amenities, RealtyObject object) {
		Arrays.asList(amenities).stream()
		.filter(a -> isNotExistsInDB(a))
		.map(a -> amenitieRepo.save(new Amenitie(a))).collect(Collectors.toSet());

		Set<Amenitie> amenit = Arrays.asList(amenities).stream().map(a->new Amenitie(a)).collect(Collectors.toSet());
		object.getAmenitie().addAll(amenit);
		//TODO
		amenit.forEach(a -> a.getRealtyObject().add(object));
	
}

	private TypeOfRealtyObject saveTypeOfObject(String typeOfRealtyObject) 
	{
		TypeOfRealtyObject typeOfObject = typeObjectRepo.findById(typeOfRealtyObject).orElse(null);
		return typeOfObject==null ? typeObjectRepo.save(new TypeOfRealtyObject(typeOfRealtyObject)) : typeOfObject;

}

	private boolean isNotExistsInDB(String a) {
		return !(amenitieRepo.existsById(a));
}

	@Override
	public String addAnnouncement(AnnouncementDto announc) {
		//TODO
		return null;
	}

	@Override
	public String editAnnnouncement(EditAnnouncDto editAnnounc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String editRealtyObject(EditAnnouncDto editAnnounc) {
		// TODO Auto-generated method stub
		return null;
	}

}
