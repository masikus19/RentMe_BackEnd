package application.business.exceptionsHandling;

import org.springframework.stereotype.Component;

import application.business.dto.fromFront.RealtyObjectDto;
import application.business.dto.google.AddressGoogleDto;
import application.business.entities.Address;
import application.business.entities.RealtyObject;
import application.business.repositories.AddressRepository;
import application.business.repositories.RealtyObjectRepository;
import application.security.services.BadRequestException;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

@Component
public class BussinesChecks {
//
    @Autowired AddressRepository addressRepoAutowired;
    @Autowired RealtyObjectRepository realtyObjectRepoAutowired;
    

    private static AddressRepository addressRepo;
    private static RealtyObjectRepository realtyObjectRepo;

    @PostConstruct
    private void plugAutowired() {
        addressRepo = addressRepoAutowired;
        realtyObjectRepo = realtyObjectRepoAutowired;
    }

//    public static void checkObject(Object object) {
//        if (object == null)
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, DTO_IS_NULL);
//    }

    public static void checkAddress(ResponseEntity<AddressGoogleDto> response) {

        if (!response.getBody().status.equals("OK")){
            System.out.println(response.getBody().status+" =======response.getBody().status");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empty address");}
    }
    
    public static void checkIsRealtyObjectExists(RealtyObjectDto dto) {

        RealtyObject realtyObject = realtyObjectRepo.existsByInfo(
                dto.getApptNumber(),
                dto.getCountryName(),
                dto.getCityName(),
                dto.getStreetName(),
                dto.getNumberOfHouse(),
                dto.getBlockNumber()
        );
        if (realtyObject != null) {
        	throw new BadRequestException("Realty object already exists");
        }
    }

//    public static void checkFullAddress(String[] splitAddres) {
//        if (splitAddres.length < 3)
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EMPTY_STREET);
//    }
//
    public static Address checkIsAddressExist(String formatted_address) {
    	
        Address address = addressRepo.findById(formatted_address).orElse(null);   
        return address;
    }
  

    public static void isAnnouncementAlreadyExists(RealtyObject object, LocalDate available)
    {
    	object.getAnnouncements().forEach(a -> {
    		if(a.getAvailable().equals(available))
    			throw new BadRequestException("Announcement already exists");
    	});
    }
}