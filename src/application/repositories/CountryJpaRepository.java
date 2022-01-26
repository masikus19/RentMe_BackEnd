package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.Country;

public interface CountryJpaRepository extends JpaRepository<Country, String>{

}
