package com.example.rentme_backend_morgan.business.repo;

import com.example.rentme_backend_morgan.business.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, String> {

}

