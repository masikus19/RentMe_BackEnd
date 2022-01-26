package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.City;

public interface CityJpaRepository extends JpaRepository<City, Integer>{

}
