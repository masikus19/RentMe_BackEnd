package application.business.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Set;

import application.business.dto.ContactOwnerDto;
import application.business.dto.EditProfileDto;
import application.business.dto.FindPlaceDto;
import application.business.entities.Announcement;
import application.business.entities.Owner;
import application.business.entities.Renter;
import application.business.entities.Tour;

public interface IRenterService {
	Renter getProfile(String loginOfRenter);
	Renter editProfile(String loginOfRenter, EditProfileDto editRenter);
	List<Announcement> findPlace(FindPlaceDto findPlace);
	List<Announcement> getFavorites(String loginOfRenter);
	List<Announcement> addFavorite(String loginOfRenter, long idOfAnnoncement);
	List<Announcement> removeFavorite(String loginOfRenter, long idOfAnnoncement);
	Set<Announcement> getHistory(String loginOfRenter);
	Set<Announcement> addAnnouncementToHistory(String loginOfRenter, long idOfAnnoncement);
	List<Tour> requestTour(LocalDate from, LocalDate to, long idOfAnnouncement);
	Owner applyToOwner(String loginOfOwner);
	String contactOwner(ContactOwnerDto contactOwner);//TODO
}
