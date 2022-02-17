package application.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.business.entities.Renter;

public interface RenterRepository extends JpaRepository<Renter, String>{

}
