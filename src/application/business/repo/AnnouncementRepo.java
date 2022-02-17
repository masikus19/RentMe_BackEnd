package application.business.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import application.business.entities.Announcement;

public interface AnnouncementRepo  extends JpaRepository<Announcement,Long> {

}
