package com.example.rentme_backend_morgan.business.repo;

import com.example.rentme_backend_morgan.business.entities.Amenitie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenitiesRepo extends JpaRepository<Amenitie, String> {


    Amenitie findAmenitieByName(String elem);
}

