package com.example.rentme_backend_morgan.business.service.logica;

import com.example.rentme_backend_morgan.business.dto.fromFront.*;
import com.example.rentme_backend_morgan.business.dto.toFront.*;
import com.example.rentme_backend_morgan.business.entities.*;
import com.example.rentme_backend_morgan.business.mapper.BusinessMapper;
import com.example.rentme_backend_morgan.business.repo.*;
import com.example.rentme_backend_morgan.business.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static com.example.rentme_backend_morgan.business.bussinesChecks.BussinesChecks.*;
import static com.example.rentme_backend_morgan.business.mapper.BusinessMapper.*;


@Service
@RequiredArgsConstructor
@Transactional
public class RenterLogica implements IRenter {

    private final RenterRepo renterRepo;
    private final OwnerRepo ownerRepo;
    private final AnnouncementRepo announcementRepo;
    private final AddressRepo addressRepo;
    private final MessageRepo messageRepo;

    @Override
    public RenterDto getProfile(String login) {
        ifLoginIsLogin(login);
        Renter renter = renterRepo.findRenterByLogin(login);
        return renterToDto(renter);
    }

    @Override
    public synchronized RenterDto editProfile(EditProfileDto dto) {
        ifLoginIsLogin(dto.getLogin());

        Renter renter = renterRepo.findRenterByLogin(dto.getLogin());

        //TODO if the user put null in some fields, what should we do?
        renter.setFirstName(dto.getFirstName());
        renter.setLastName(dto.getLastName());
        renter.setNumberTelephone(dto.getNumberTelephone());
        renter.setEmail(dto.getEmail());
        renter.setPhotoRenter(dto.getPhoto());

        renterRepo.save(renter);

        return renterToDto(renter);
    }

    @Override
    @Transactional
    public Set<PartOfAnnDto> findObjectByPlace(FindPlaceDto dto) {
        ifLoginIsLogin(dto.getLogin());

        List<Integer> room = Arrays.asList(dto.getRooms());

        Predicate<Announcement> pricePredicate = ann ->
                        ann.getPrice() >= dto.getMinPrice()
                        && ann.getPrice() <= dto.getMaxPrice();
        Predicate<RealtyObject> bedroomsPredicate = object ->
                room.contains(object.getBedrooms());

        Set<PartOfAnnDto> collect = addressRepo.findAddressByCountryAndCity(dto.getCountry(), dto.getCity())
                .stream().flatMap(elem -> elem.getRealtyObjects()
                        .stream().filter(bedroomsPredicate))
                .flatMap(obj -> obj.getAnnouncement()
                        .stream()).filter(pricePredicate)
                .map(this::PartInfoToDto).collect(Collectors.toSet());

        return collect;
    }

    @Override
    @Transactional
    public AnnouncementDtoToFront getFullDataByPlace(long idAnnouncement) {
        Announcement announcement = announcementRepo.findAnnouncementById(idAnnouncement);
        return returnFullInformation(announcement);
    }

    @Override
    public synchronized Set<AnnoncementDto> addFavorite(String login, AnnoncementDto dto) {
        ifLoginIsLogin(login);
        Renter renter = renterRepo.findRenterByLogin(login);

        renter.getAnnouncementF().add(announcementToEntity(dto));
        renterRepo.save(renter);
        return returnSetAnnouncementDto(renter);
    }

    @Override
    public Set<AnnoncementDto> getFavorites(String login) {
        ifLoginIsLogin(login);
        Renter renter = renterRepo.findRenterByLogin(login);

        return returnSetAnnouncementDto(renter);
    }

    @Override
    public synchronized Set<AnnoncementDto> removeFavorite(String login, long idAnnouncement) {
        ifLoginIsLogin(login);
        Renter renter = renterRepo.findRenterByLogin(login);
        renter.getAnnouncementF().removeIf(announcement -> announcement.getId() == idAnnouncement);
        renterRepo.save(renter);
        return returnSetAnnouncementDto(renter);
    }

    @Override
    public Set<AnnoncementDto> addHistory(String login, AnnoncementDto dto) {
        ifLoginIsLogin(login);
        Renter renter = renterRepo.findRenterByLogin(login);

        renter.getAnnouncementH().add(announcementToEntity(dto));
        renterRepo.save(renter);
        return returnSetAnnouncementDto(renter);

    }

    @Override
    public Set<AnnoncementDto> getHistory(String login) {
        ifLoginIsLogin(login);
        Renter renter = renterRepo.findRenterByLogin(login);
        return returnSetAnnouncementDto(renter);
    }

    @Override
    @Transactional
    public synchronized Set<AnnoncementDto> removeHistory(String login, long idAnnouncement) {
        ifLoginIsLogin(login);
        Renter renter = renterRepo.findRenterByLogin(login);
        renter.getAnnouncementH().removeIf(announcement -> announcement.getId() == idAnnouncement);
        renterRepo.save(renter);
        return returnSetAnnouncementDto(renter);
    }


    @Override
    public synchronized String requestTour(RequestTourDto dto) {
        ifLoginIsLogin(dto.getLoginRenter());

        Renter renter = renterRepo.findRenterByLogin(dto.getLoginRenter());
        Announcement announcement = announcementRepo.findAnnouncementById(dto.getIdAnnouncement());
        Owner owner = ownerRepo.findOwnerByLogin(announcement.getRealtyObject().getOwner().getLogin());

        messageRepo.save(new Message(
                renter,
                owner,
                "RENTER",
                "OWNER",
                dto.getFrom(),
                dto.getTo(),
                announcement,
                dto.getMessageText()));

        return "Some text";
    }

    @Override
    public OwnerDto applyToOwner(String login) {
        ifLoginIsLogin(login);
        Owner owner = ownerRepo.findOwnerByLogin(login);
        return ownerToDto(owner);
    }

    @Override
    public synchronized String sendMessageToOwner(MessageOwnerRenterDto dto) {
        ifLoginIsLogin(dto.getLoginRenter());

        Renter renter = renterRepo.findRenterByLogin(dto.getLoginRenter());
        Owner owner = ownerRepo.findOwnerByLogin(dto.getLoginOwner());

        messageRepo.save(new Message(
                renter,
                owner,
                "RENTER",
                "OWNER",
                dto.getMessageText()));

        return "Some text";
    }

    //=============================help methods================================================
    private PartOfAnnDto PartInfoToDto(Announcement object) {
        return announcementToPartDto(object);
    }

    private Set<AnnoncementDto> returnSetAnnouncementDto(Renter renter) {
        return renter.getAnnouncementF().stream()
                .map(BusinessMapper::announcementToDto).collect(Collectors.toSet());
    }

    private AnnouncementDtoToFront returnFullInformation(Announcement announcement) {
        return BusinessMapper.fullInfoAboutObject(announcement);
    }
}