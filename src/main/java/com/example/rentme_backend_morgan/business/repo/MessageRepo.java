package com.example.rentme_backend_morgan.business.repo;

import com.example.rentme_backend_morgan.business.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

}

