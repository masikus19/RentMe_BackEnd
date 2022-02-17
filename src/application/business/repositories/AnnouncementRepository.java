package application.business.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.business.entities.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long>{
	
////	String country;
////	String city;
////	int minPrice;
////	int maxPrice;
////	Integer[] rooms;
//	@Query("select announcement from Announcement announcement where (announcement.price between ?3 and ?4) and"
//			+ "(announcement.realtyObject.address.countryName = ?1 and announcement.realtyObject.address.cityName = ?2) and"
//			+ "(announcement.realtyObject.bedrooms in ?5)")
//	List<Announcement> findPlace(String countryName, String cityName, int minPrice, int maxPrice, int[] rooms);
//
//	List<Announcement> findByPriceBetween(int minPrice, int maxPrice);

}
