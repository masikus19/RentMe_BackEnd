package com.example.rentme_backend_morgan.business.bussinesChecks;

import com.example.rentme_backend_morgan.business.dto.ResponseDto.AddressGoogleDto;
import com.example.rentme_backend_morgan.business.dto.ResponseDto.Candidates;
import com.example.rentme_backend_morgan.business.dto.fromFront.*;
import com.example.rentme_backend_morgan.business.entities.Address;
import com.example.rentme_backend_morgan.business.entities.RealtyObject;
import com.example.rentme_backend_morgan.business.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

import static com.example.rentme_backend_morgan.business.api.BusinessResponses.*;
import static com.example.rentme_backend_morgan.business.service.google.ConfigRestForGoogle.createCandidates;


@Component
@Transactional
public class BussinesChecks {

    @Autowired
    AddressRepo addressRepo;
    @Autowired
    RealtyObjectRepo realtyObjectRepo;

    private static AddressRepo addressRepoY;
    private static RealtyObjectRepo realtyObjectRepoO;

    @PostConstruct
    private void plugAutowired() {
        addressRepoY = addressRepo;
        realtyObjectRepoO = realtyObjectRepo;
    }

    public static void ifLoginIsLogin(String login) {
        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals(login))
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, DTO_IS_NULL);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public static void checkIsRealtyObjectExists(RealtyObjectDto dto) {

        RealtyObject realtyObject = realtyObjectRepoO.existsByInfo(
                dto.getApptNumber(),
                dto.getCountryName(),
                dto.getCityName(),
                dto.getStreetName(),
                dto.getNumberHouse(),
                dto.getBlockNumber()
        );
        if (realtyObject != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, REALTY_OBJECT_ALREADY_EXISTS);
        }
    }

    public static void checkAddress(ResponseEntity<AddressGoogleDto> response) {
        if (!response.getBody().status.equals("OK"))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, EMPTY_ADDRESS);
    }

    public static Address checkIsAddressExist(String formatted_address) {

        Address address = addressRepoY.findByFullAddressId(formatted_address);
//        Address address = addressRepoY.getById(formatted_address);

        return address;
    }

    public static RealtyObject checkIsAnnouncementExist(AnnoncementDto dto) {

        RealtyObject realtyObject = realtyObjectRepoO.findRealtyObject(dto.getIdObject());
        realtyObject.getAnnouncement().forEach(ann ->
        {
            if (ann.getAvailable().equals(dto.getAvailable())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, EMPTY_ADDRESS);
            }
        });
        return realtyObject;
    }
}