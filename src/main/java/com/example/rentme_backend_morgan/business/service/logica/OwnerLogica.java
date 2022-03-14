package com.example.rentme_backend_morgan.business.service.logica;


import com.example.rentme_backend_morgan.business.dto.ResponseDto.Candidates;
import com.example.rentme_backend_morgan.business.dto.ResponseDto.Location;
import com.example.rentme_backend_morgan.business.dto.fromFront.*;
import com.example.rentme_backend_morgan.business.entities.*;
import com.example.rentme_backend_morgan.business.repo.*;
import com.example.rentme_backend_morgan.business.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.example.rentme_backend_morgan.business.bussinesChecks.BussinesChecks.*;
import static com.example.rentme_backend_morgan.business.mapper.BusinessMapper.*;
import static com.example.rentme_backend_morgan.business.service.google.ConfigRestForGoogle.createCandidates;

@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class OwnerLogica implements IOwner {


    private final RenterRepo renterRepo;
    private final OwnerRepo ownerRepo;
    private final AnnouncementRepo announcementRepo;
    private final MessageRepo messageRepo;
    private final RealtyObjectRepo realtyObjectRepo;
    private final AddressRepo addressRepo;
    private final TypeOfRealtyObjectRepo typeOfRealtyObjectRepo;
    private final PhotoRepo photoRepo;
    private final AmenitiesRepo amenitiesRepo;

    @Override
    public OwnerDto getProfile(String loginOwner) {
        ifLoginIsLogin(loginOwner);
        Owner owner = ownerRepo.findOwnerByLogin(loginOwner);
        return ownerToDto(owner);
    }

    @Override
    public OwnerDto editProfile(EditProfileDto dto) {
        ifLoginIsLogin(dto.getLogin());

        Owner owner = ownerRepo.findOwnerByLogin(dto.getLogin());

        owner.setFirstName(dto.getFirstName());
        owner.setLastName(dto.getLastName());
        owner.setNumberTelephone(dto.getNumberTelephone());
        owner.setEmail(dto.getEmail());
        owner.setAboutMe(dto.getAboutMe());
        owner.setPhotoOwner(dto.getPhoto());
        ownerRepo.save(owner);

        return ownerToDto(owner);
    }

    @Override
    @Transactional
            (transactionManager = "businessTransactionManager")
    public synchronized String addRealityObject(RealtyObjectDto dto) {

        ifLoginIsLogin(dto.getLoginOwner());
        checkIsRealtyObjectExists(dto);

        saveAmenities(dto.getAmenities());

        Owner owner = ownerRepo.findOwnerByLogin(dto.getLoginOwner());

        Address address = getAddress(dto);

        TypeOfRealtyObject typeOfRealtyObject =
                getTypeOfRealtyObject(dto.getTypeOfRealtyObject());

        RealtyObject realtyObject = new RealtyObject(
                address,
                owner,
                dto.getNameRentObject(),
                dto.getApptNumber(),
                dto.getSize(),
                dto.getFloor(),
                dto.getRooms(),
                dto.getAvatarPhoto(),
                typeOfRealtyObject
        );

        savePhotos(realtyObject, dto.getPhotos());

        saveObjectAmenit(realtyObject, dto.getAmenities());

        return "Realty object is added to DB";
    }

    public void saveObjectAmenit(RealtyObject realtyObject, Set<String> amenities) {

        amenities.forEach(elem -> {
            realtyObject.getAmenitiess().add(amenitiesRepo.findAmenitieByName(elem));
        });

    }

    public void saveAmenities(Set<String> amenities) {
        amenities.forEach(elem -> {
            amenitiesRepo.save(new Amenitie(elem));
        });
    }

    @Transactional
            (transactionManager = "businessTransactionManager")
    public TypeOfRealtyObject getTypeOfRealtyObject(String name) {

        TypeOfRealtyObject typeOfRealtyObject =
//                typeOfRealtyObjectRepo.getById(name);
                typeOfRealtyObjectRepo.findTypeOfRealtyObjectByName(name);


        if (typeOfRealtyObject == null) {
            typeOfRealtyObject = new TypeOfRealtyObject(name);
        }

        return typeOfRealtyObject;
    }

    @Transactional
            (transactionManager = "businessTransactionManager")
    public Address getAddress(RealtyObjectDto dto) {
        Candidates candidates = createCandidates(dto);
        String formatted_address = candidates.getFormatted_address();
        Location location = candidates.getGeometry().getLocation();

        Address address = checkIsAddressExist(formatted_address);

        if (address == null) {
            address = new Address(
                    formatted_address,
                    dto.getCountryName(),
                    dto.getCityName(),
                    dto.getStreetName(),
                    dto.getBlockNumber(),
                    dto.getNumberHouse(),
                    location.getLat(),
                    location.getLng());
        }

        return address;
    }

    @Transactional
            (transactionManager = "businessTransactionManager")
    public void savePhotos(RealtyObject realtyObject, List<String> photos) {
        photos.forEach(ph ->
        {
            photoRepo.save(new Photo(realtyObject, ph));
        });
    }

    @Override
    public String addAnnouncement(AnnoncementDto dto) {
        ifLoginIsLogin(dto.getLoginOwner());

        RealtyObject realtyObject = checkIsAnnouncementExist(dto);
        announcementRepo.save(
                new Announcement(
                        realtyObject,
                        dto.getAvailable(),
                        dto.getPrice(),
                        dto.getMinDurationOfStay(),
                        dto.getSecurityDeposit(),
                        dto.getCancellationTime()));

        return "Announcement is added to DB";
    }

    @Override
    public RealtyObjectDto editRealityObject(RealtyObjectDto dto) {
        ifLoginIsLogin(dto.getLoginOwner());
        return null;
    }

    @Override
    public Set<RealtyObjectDto> getRealityObjectS(String loginOwner) {
        ifLoginIsLogin(loginOwner);
        return null;
    }

    @Override
    public Set<RealtyObjectDto> removeObject(String loginOwner, int idObject) {
        ifLoginIsLogin(loginOwner);
        return null;
    }

    @Override
    public synchronized String sendMessageToRenter(MessageOwnerRenterDto dto) {
        ifLoginIsLogin(dto.getLoginOwner());

        Renter renter = renterRepo.findRenterByLogin(dto.getLoginRenter());
        Owner owner = ownerRepo.findOwnerByLogin(dto.getLoginOwner());

        messageRepo.save(new Message(
                renter,
                owner,
                "OWNER",
                "RENTER",
                dto.getMessageText()));

        return "Some text";
    }
}