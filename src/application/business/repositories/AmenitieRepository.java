package application.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.business.entities.Amenitie;

public interface AmenitieRepository extends JpaRepository<Amenitie, String>{

}
