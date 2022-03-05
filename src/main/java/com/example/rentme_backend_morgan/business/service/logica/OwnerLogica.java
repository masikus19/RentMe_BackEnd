package com.example.rentme_backend_morgan.business.service.logica;


import com.example.rentme_backend_morgan.business.dto.fromFront.*;
import com.example.rentme_backend_morgan.business.entities.*;
import com.example.rentme_backend_morgan.business.repo.*;
import com.example.rentme_backend_morgan.business.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.example.rentme_backend_morgan.business.bussinesChecks.BussinesChecks.*;
import static com.example.rentme_backend_morgan.business.mapper.BusinessMapper.*;

@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class OwnerLogica implements IOwner {


    private final RenterRepo renterRepo;
    private final OwnerRepo ownerRepo;
    private final AnnouncementRepo announcementRepo;
    private final MessageRepo messageRepo;
    private final RealtyObjectRepo realtyObjectRepo;
    private final PhotoRepo photoRepo;

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
    public synchronized String addRealityObject(RealtyObjectDto dto) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
//        ifLoginIsLogin(dto.getLoginOwner());
//        checkIsRealtyObjectExists(dto);

//        Address address = checkIsAddressExist(dto);
//        Owner owner = ownerRepo.findOwnerByLogin(dto.getLoginOwner());


//        realtyObjectRepo.save(new RealtyObject());
        System.out.println("2================");
//        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return "Realty object is added to DB";
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

    //==================================================================================

//    public void savePhoto(List<String> photo, RealtyObject realtyObject) {
//        if (!photo.isEmpty()) {
//            photo.forEach(ph -> {
//                photoRepo.save(new Photo(realtyObject, ph));
//            });
//        }
//    }
}