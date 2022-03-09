package com.example.rentme_backend_morgan.business.repo;

import com.example.rentme_backend_morgan.business.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AddressRepo extends JpaRepository<Address, String> {

//    boolean existsByFullAddressId(String formatted_address);
    Address findByFullAddressId(String formatted_address);

    List<Address> findAddressByCountryAndCity(String country, String city);

}

