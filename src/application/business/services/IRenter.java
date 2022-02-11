package application.business.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import application.business.dto.ContactOwnerDto;
import application.business.dto.EditProfileDto;
import application.business.dto.FindPlaceDto;
import application.business.dto.OwnerDto;
import application.business.dto.RenterDto;
import application.business.dto.AnnouncementDto;

public interface IRenter {
	
	RenterDto getProfile(String loginOfRenter);
	RenterDto editProfile(EditProfileDto editRenter);
	List<AnnouncementDto> findPlace(FindPlaceDto findPlace);
	List<AnnouncementDto> getFavorites(String loginOfRenter);
	List<AnnouncementDto> addFavorite(long idOfAnnoncement);
	List<AnnouncementDto> removeFavorite(long idOfAnnoncement);
	Set<AnnouncementDto> getHistory(String loginOfRenter);
	Set<AnnouncementDto> addAnnouncementToHistory(long idOfAnnoncement);
	String requestTour(LocalDate from, LocalDate to, int idOfAnnouncement);
	OwnerDto applyToOwner(String loginOfOwner);
	String contactOwner(ContactOwnerDto contactOwner);//TODO

}
