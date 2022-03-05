package com.example.rentme_backend_morgan.security.repo;

import com.example.rentme_backend_morgan.security.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface AccountRepo extends JpaRepository<Account, String> {

}
