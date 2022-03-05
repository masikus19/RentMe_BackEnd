package com.example.rentme_backend_morgan.business.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.example.rentme_backend_morgan.business.api.BusinessApiEndPoints.*;

@RestController
@RequestMapping(GUEST)
public class GuestController {


    @GetMapping(GUEST_FIND_PLACE)
    public String findPlace() {

        return "user";
    }

}