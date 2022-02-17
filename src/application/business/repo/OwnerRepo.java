package application.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import application.business.entities.Owner;

public interface OwnerRepo extends JpaRepository<Owner, String> {

}
