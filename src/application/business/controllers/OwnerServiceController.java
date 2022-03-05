package application.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.business.dto.fromFront.AnnouncementDto;
import application.business.dto.fromFront.EditProfileDto;
import application.business.dto.fromFront.RealtyObjectDto;
import application.business.dto.toFront.OwnerDto;
import application.business.services.interfaces.IOwner;

import static application.config.API.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/app/owner")
public class OwnerServiceController {
	@Autowired IOwner service;
	
	@GetMapping(GET_PROFILE_OWNER)
	public OwnerDto getProfile(String login)
	{
		return service.getProfile(login); 
	}
	
	@PutMapping(EDIT_PROFILE_OWNER)
	public OwnerDto editProfile(String login, @Valid @RequestBody EditProfileDto editProfile)
	{
		return service.editProfile(login, editProfile);
	}
	
	@PostMapping(ADD_REALTY_OBJECT)
	public String addRealtyObject(@Valid @RequestBody RealtyObjectDto realtyObject)
	{
		return service.addRealtyObject(realtyObject);
	}

	@PostMapping(ADD_ANNOUNCEMENT)
	public String addAnnouncement(@Valid @RequestBody AnnouncementDto announc)
	{
		return service.addAnnouncement(announc);
	}

}
