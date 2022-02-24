package application.business.services.interfaces;

import application.business.dto.fromFront.AnnouncementDto;
import application.business.dto.fromFront.EditAnnouncDto;
import application.business.dto.fromFront.EditProfileDto;
import application.business.dto.fromFront.RealtyObjectDto;
import application.business.dto.toFront.OwnerDto;


public interface IOwner 
{
	OwnerDto getProfile(String loginOfOwner);
	OwnerDto editProfile(String login, EditProfileDto editProfile);
	String addRealtyObject(RealtyObjectDto realtyObject);
	String addAnnouncement(AnnouncementDto announc);
	String editAnnnouncement(EditAnnouncDto editAnnounc); 
	String editRealtyObject(EditAnnouncDto editAnnounc); 
	
	

}
