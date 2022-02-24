package application.business.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.business.entities.RealtyObject;

public interface RealtyObjectRepository extends JpaRepository<RealtyObject, Long>{

	@Query("select realtyObject from RealtyObject realtyObject where realtyObject.address.countryName = ?1 and realtyObject.address.cityName = ?2") 
	List<RealtyObject> findByCountryAndCity(String countryName, String cityName);
	
	@Query("select realtyObject from RealtyObject realtyObject where realtyObject.appt=?1 and realtyObject.address.countryName=?2 and realtyObject.address.cityName=?3 and realtyObject.address.street=?4 and realtyObject.address.numberOfHouse=?5 and realtyObject.block=?6")
	RealtyObject existsByInfo(String apptNumber, String countryName, String cityName, String streetName,
			int numberOfHouse, String blockNumber);


}

