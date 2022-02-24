package application.business.controllers;

import static application.config.API.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.business.dto.fromFront.EditProfileDto;
import application.business.dto.toFront.RenterDto;
import application.business.dto1.OwnerDto;
import application.business.entities.Owner;
import application.business.entities.Renter;
import application.business.services.interfaces.IRenter;
import application.business.services.interfacesImplementation.RentMe;

@RestController
@RequestMapping(APP)
@Validated
public class RentMeController {
	
	@Autowired RentMe servise;
	
	
	@PostMapping(ADD_RENTER)
	public RenterDto addUser(@Valid @RequestBody RenterDto renter) 
	{
		Renter rent = servise.addRenter(renter);
		return new RenterDto(rent.getLogin(), rent.getFirstName(), rent.getLastName(), rent.getNumberTelephone(),  rent.getEmail(), rent.getPhoto());
	}
	
	
	@PostMapping(ADD_LESSOR)
	public OwnerDto addOwner(@RequestBody OwnerDto owner) 
	{
		Owner own = servise.addOwner(owner);
		return new OwnerDto(own.getLogin(), own.getFirstName(), own.getLastName(), own.getNumberTelephone(),  own.getEmail(), own.getAboutMe(), own.getPhoto());
	}
	
}
