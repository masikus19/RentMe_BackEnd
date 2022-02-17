package application.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.business.entities.Owner;

public interface OwnerRepository extends JpaRepository<Owner, String>{

}
