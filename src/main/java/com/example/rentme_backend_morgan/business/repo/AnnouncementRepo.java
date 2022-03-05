package com.example.rentme_backend_morgan.business.repo;

import com.example.rentme_backend_morgan.business.entities.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AnnouncementRepo extends JpaRepository<Announcement, Long> {

    boolean existsByAvailable(LocalDate available);

    Announcement findAnnouncementById(long idAnnouncement);
}
