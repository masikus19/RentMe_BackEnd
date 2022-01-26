package application.services;

import java.util.Set;

import application.entities.City;
import application.entities.Country;

public interface ICountryCityService {

	Country addCountry(String name, double population);
	City addCity(String name, double population, String countryId);
	Set<City> getCities(String countryId);
	Country removeCountry(String countryId);

}