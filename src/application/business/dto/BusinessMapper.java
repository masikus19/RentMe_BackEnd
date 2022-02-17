package application.business.dto;

import java.util.Set;
import java.util.stream.Collectors;

import application.business.entities.Address;
import application.business.entities.Announcement;
import application.business.entities.RealtyObject;

public class BusinessMapper 
{
	public static FindPlaceResponseDto toFindPlaceResponseDto(Announcement announc)
	{
		RealtyObject object = announc.getRealtyObject();
		Address address = object.getAddress();
		return 	new FindPlaceResponseDto(announc.getId(), object.getPhotos().get(0).getUrl(), 
					object.getSize(), object.getBedrooms(), object.getBathrooms(), announc.getPrice(), address.getLat(), address.getLng());			
	}
	
	public static Set<FindPlaceResponseDto> toSetFindPlaceResponseDto(Set<Announcement> announcements)
	{
		return announcements.stream().map(BusinessMapper::toFindPlaceResponseDto).collect(Collectors.toSet());
	}

}
