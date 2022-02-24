package application.business.services.interfaces;

import java.util.List;
import java.util.Set;

import application.business.dto.fromFront.EditProfileDto;
import application.business.dto.fromFront.FindPlaceDto;
import application.business.dto.fromFront.MessageDto;
import application.business.dto.fromFront.RequestTourDto;
import application.business.dto.toFront.FindPlaceResponseDto;
import application.business.dto.toFront.OwnerDto;
import application.business.dto.toFront.RenterDto;

public interface IRenter {
	
	RenterDto getProfile(String loginOfRenter);
	RenterDto editProfile(String login, EditProfileDto editProfile);
	List<FindPlaceResponseDto> findPlace(FindPlaceDto findPlace);
	Set<FindPlaceResponseDto> getFavorites(String loginOfRenter);
	String addFavorite(String loginOfRenter, long idOfAnnoncement);
	String removeFavorite(String loginOfRenter, long idOfAnnoncement);
	Set<FindPlaceResponseDto> getHistory(String loginOfRenter);
	String addAnnouncementToHistory(String loginOfRenter, long idOfAnnoncement);
	String requestTour(RequestTourDto data);
	OwnerDto applyToOwner(String loginOfOwner);
	String contactOwner(MessageDto contactOwner);

}
