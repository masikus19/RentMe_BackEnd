package application.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.business.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer>{

}
