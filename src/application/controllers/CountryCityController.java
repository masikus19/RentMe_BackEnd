package application.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import application.entities.City;
import application.entities.Country;
import application.services.ICountryCityService;

@RestController
public class CountryCityController {
	
	@Autowired ICountryCityService service;

	@PostMapping("/addCountry")
	public Country addCountry(String name, double population) {
		return service.addCountry(name, population);
	}
	
	@PostMapping("/addCity")
	public City addCity(String name, double population, String countryId) {
		return service.addCity(name, population, countryId);
	}
	
	@GetMapping("/getCities")
	public Set<City> getCities(String countryId){
		return service.getCities(countryId);
	}
	
	@DeleteMapping("/removeCountry")
	public Country removeCountry(String countryId) {
		return service.removeCountry(countryId);
	}
}
