package application.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.business.entities.Address;

public interface AddressRepository extends JpaRepository<Address, String>{

	
}
