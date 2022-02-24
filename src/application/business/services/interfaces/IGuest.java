package application.business.services.interfaces;

import java.util.List;

import application.business.dto.fromFront.AnnouncementDto;
import application.business.dto.fromFront.FindPlaceDto;
import application.business.dto1.AnnouncementsByFiltersDto;
import application.business.entities.Announcement;

public interface IGuest {
	List<Announcement> getAnnouncementsByCity(String cityName);
	List<Announcement> getAnnouncementsByFilters(AnnouncementsByFiltersDto filters);


}
