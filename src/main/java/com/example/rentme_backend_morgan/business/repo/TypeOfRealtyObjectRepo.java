package com.example.rentme_backend_morgan.business.repo;

import com.example.rentme_backend_morgan.business.entities.TypeOfRealtyObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeOfRealtyObjectRepo extends JpaRepository<TypeOfRealtyObject, String> {


    TypeOfRealtyObject findTypeOfRealtyObjectByName(String name);
}


