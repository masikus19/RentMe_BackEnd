package com.example.rentme_backend_morgan.business.controllers;


import com.example.rentme_backend_morgan.business.dto.fromFront.*;
import com.example.rentme_backend_morgan.business.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.Set;

import static com.example.rentme_backend_morgan.business.api.BusinessApiEndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(OWNER)
@Valid
public class OwnerController {

    private final IOwner iOwner;

    @PostMapping(OWNER_ADD_RENT_OBJECT)
    public String addRentObject(
            @RequestBody RealtyObjectDto dto) {
        return iOwner.addRealityObject(dto);
    }

    @PostMapping(OWNER_ADD_ANNOUNCEMENT)
    public String addAnnouncement(
            @RequestBody(required = true) AnnoncementDto dto) {
        return iOwner.addAnnouncement(dto);
    }

    @GetMapping(OWNER_GET_PROFILE)
    public OwnerDto getProfile(
            @Pattern(regexp = "^[A-Za-z0-9]*$") String loginOwner) {
        return iOwner.getProfile(loginOwner);
    }

    @PutMapping(OWNER_EDIT_PROFILE)
    public OwnerDto editProfile(
            @RequestBody(required = true) EditProfileDto dto) {
        return iOwner.editProfile(dto);
    }

    @PutMapping(OWNER_EDIT_OBJECT)
    public RealtyObjectDto editObject(
            @RequestBody(required = true) RealtyObjectDto dto) {
        return iOwner.editRealityObject(dto);
    }

    @GetMapping(OWNER_GET_OBJECTS)
    public Set<RealtyObjectDto> getObjects(
            @Pattern(regexp = "^[A-Za-z0-9]*$") String loginOwner) {
        return iOwner.getRealityObjectS(loginOwner);
    }

    @DeleteMapping(OWNER_REMOVE_OBJECT)
    public Set<RealtyObjectDto> removeObject(
            @Pattern(regexp = "^[A-Za-z0-9]*$") String loginOwner,
            int idObject) {
        return iOwner.removeObject(loginOwner, idObject);
    }

    @PostMapping(OWNER_SEND_MESSAGE)
    public String removeObject(
            @RequestBody(required = true) MessageOwnerRenterDto dto) {
        return iOwner.sendMessageToRenter(dto);
    }

}