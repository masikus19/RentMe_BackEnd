package application.business.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.business.entities.RealtyObject;

public interface RealtyObjectRepository extends JpaRepository<RealtyObject, Long>{

	@Query("select realtyObject from RealtyObject realtyObject where realtyObject.address.countryName = ?1 and realtyObject.address.cityName = ?2") 
	List<RealtyObject> findByCountryAndCity(String countryName, String cityName);

}
