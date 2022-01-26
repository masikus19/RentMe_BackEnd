package application.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.entities.City;
import application.entities.Country;
import application.repositories.CityJpaRepository;
import application.repositories.CountryJpaRepository;

@Service
public class CountryCityService implements ICountryCityService {

	@Autowired CityJpaRepository cityRepo;
	@Autowired CountryJpaRepository countryRepo;
	
	@Override
	public Country addCountry(String name, double population) {
		Country country = new Country(name, population);
		countryRepo.save(country);
		return country;
	}
	
	@Override
	public City addCity(String name, double population, String countryId) {
		City city = new City(name, population, countryRepo.findById(countryId).orElse(null));
		cityRepo.save(city);
		return city;
	}
	
	@Override
	public Set<City> getCities(String countryId){
		return countryRepo.findById(countryId).orElse(null).getCities();
	}
	
	@Override
	public Country removeCountry(String countryId) {
		Country country = countryRepo.findById(countryId).orElse(null);
		countryRepo.deleteById(countryId);
		return country;
	}
}
