package application.business.services;

import java.util.List;
import java.util.Set;

import application.business.dto.EditProfileDto;
import application.business.dto.FindPlaceDto;
import application.business.dto.FindPlaceResponseDto;
import application.business.dto.MessageToOwnerDto;
import application.business.dto.OwnerDto;
import application.business.dto.RenterDto;
import application.business.dto.RequestTourDto;

public interface IRenter {
	
	RenterDto getProfile(String loginOfRenter);
	RenterDto editProfile(String login, EditProfileDto editRenter);
	List<FindPlaceResponseDto> findPlace(FindPlaceDto findPlace);
	Set<FindPlaceResponseDto> getFavorites(String loginOfRenter);
	String addFavorite(String loginOfRenter, long idOfAnnoncement);
	String removeFavorite(String loginOfRenter, long idOfAnnoncement);
	Set<FindPlaceResponseDto> getHistory(String loginOfRenter);
	String addAnnouncementToHistory(String loginOfRenter, long idOfAnnoncement);
	String requestTour(RequestTourDto data);
	OwnerDto applyToOwner(String loginOfOwner);
	String contactOwner(MessageToOwnerDto contactOwner);

}
