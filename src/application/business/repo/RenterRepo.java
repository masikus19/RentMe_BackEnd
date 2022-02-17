package application.business.repo;

import application.business.entities.Renter;

import org.springframework.data.jpa.repository.JpaRepository;

	public interface RenterRepo extends JpaRepository<Renter, String> {

	 Renter findRenterByLogin(String loginOfRenter);
	}

