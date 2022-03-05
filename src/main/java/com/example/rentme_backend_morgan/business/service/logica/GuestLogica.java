package com.example.rentme_backend_morgan.business.service.logica;

import com.example.rentme_backend_morgan.business.dto.fromFront.*;
import com.example.rentme_backend_morgan.business.dto.toFront.*;
import com.example.rentme_backend_morgan.business.repo.AnnouncementRepo;
import com.example.rentme_backend_morgan.business.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestLogica implements IGuest {

    private final AnnouncementRepo announcementRepo;

    @Override
    public List<AnnouncementDtoToFront> findPlace(FindPlaceDto findPlace) {
        return null;
    }

    @Override
    public String writeToAdmin(String massage) {
        return null;
    }
}