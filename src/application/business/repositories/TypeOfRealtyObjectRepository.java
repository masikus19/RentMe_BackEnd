package application.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.business.entities.TypeOfRealtyObject;

public interface TypeOfRealtyObjectRepository extends JpaRepository<TypeOfRealtyObject, String>{

}
