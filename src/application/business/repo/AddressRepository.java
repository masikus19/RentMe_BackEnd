package application.business.repo;
import application.business.entities.Address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	List<Address> findAllByLocation(String country, String city);
}
