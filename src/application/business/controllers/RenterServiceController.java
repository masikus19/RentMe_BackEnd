package application.business.controllers;

import java.util.List;
import java.util.Set;

import static application.config.API.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.business.dto.*;
import application.business.dto.fromFront.EditProfileDto;
import application.business.dto.fromFront.FindPlaceDto;
import application.business.dto.fromFront.MessageDto;
import application.business.dto.fromFront.RequestTourDto;
import application.business.dto.toFront.FindPlaceResponseDto;
import application.business.dto.toFront.OwnerDto;
import application.business.dto.toFront.RenterDto;
import application.business.services.interfaces.IRenter;

@RestController
@RequestMapping(value = "/app/renter")
@Validated
public class RenterServiceController {
	@Autowired IRenter renterService;
	
	@GetMapping(GET_PROFILE_RENTER)
	public RenterDto getProfile(String login)
	{
		return renterService.getProfile(login);
	}
	
	@PutMapping(EDIT_PROFILE_RENTER)
	public RenterDto editProfile(String login, @Valid @RequestBody EditProfileDto editProfile)
	{
		return renterService.editProfile(login, editProfile);
	}
	
	@GetMapping(FIND_PLACE)
	public List<FindPlaceResponseDto> findPlace(@Valid @RequestBody FindPlaceDto data)
	{
		return renterService.findPlace(data);
	}
	
	@GetMapping(GET_FAVORITES)
	public Set<FindPlaceResponseDto> getFavorites(String loginOfRenter)
	{
		return renterService.getFavorites(loginOfRenter);
	}
	
	@PutMapping(ADD_FAVORITE)
	public String addFavorite(String loginOfRenter, long idOfAnnoncement)
	{
		return renterService.addFavorite(loginOfRenter, idOfAnnoncement);
	}
	
	@DeleteMapping(REMOVE_FAVORITE)
	public String removeFavorite(String loginOfRenter, long idOfAnnoncement) 
	{
		return renterService.removeFavorite(loginOfRenter, idOfAnnoncement);
	}
	
	@GetMapping(GET_HISTORY)
	public Set<FindPlaceResponseDto> getHistory(String loginOfRenter)
	{
		return renterService.getHistory(loginOfRenter);
	}
	
	@PutMapping(ADD_ANNOUNCEMENT_TO_HISTORY)
	public String addAnnouncementToHistory(String loginOfRenter, long idOfAnnoncement)
	{
		return renterService.addAnnouncementToHistory(loginOfRenter, idOfAnnoncement);
	}
	
	@PostMapping(REQUEST_TOUR)
	public String requestTour(@Valid @RequestBody RequestTourDto data)
	{
		return renterService.requestTour(data);
	}
	
	@GetMapping(APPLY_TO_OWNER)
	public OwnerDto applyToOwner(String loginOfOwner)
	{
		return renterService.applyToOwner(loginOfOwner);
	}
	
	@PostMapping(CONTACT_OWNER)
	public String contactOwner(@RequestBody MessageDto data)
	{
		return renterService.contactOwner(data);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
