package com.example.rentme_backend_morgan.security.repo;

import com.example.rentme_backend_morgan.security.entities.Account;
import com.example.rentme_backend_morgan.security.entities.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PasswordRepo extends CrudRepository<Password, Long> {

    Password findByAccount(Account account);
}
