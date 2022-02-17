package application.business.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import application.business.entities.Tour;


public interface TourRepo extends JpaRepository<Tour, Integer> {

	List<Tour> findTour(LocalDate from, LocalDate to, long idOfAnnouncement);
}
