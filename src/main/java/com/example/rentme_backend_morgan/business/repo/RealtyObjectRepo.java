package com.example.rentme_backend_morgan.business.repo;

import com.example.rentme_backend_morgan.business.entities.RealtyObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RealtyObjectRepo extends JpaRepository<RealtyObject, Integer> {

    @Query(value = "SELECT * FROM realtyobject WHERE id=?1", nativeQuery = true)
    RealtyObject findRealtyObject(long idObject);

    @Query(value = "SELECT * FROM realtyobject, address " +
            "WHERE " +
            "realtyobject.appt_number=?1 " +
            "AND address.country_name=?2 " +
            "AND address.city_name=?3 " +
            "AND address.street_name=?4 " +
            "AND address.number_house=?5 " +
            "AND address.block_number=?6", nativeQuery = true)
    RealtyObject existsByInfo(String apptNumber,
                              String countryName,
                              String cityName,
                              String streetName,
                              int number_House,
                              String blockNumber);

}

