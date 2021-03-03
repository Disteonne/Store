package com.netcracker.store.repository;

import com.netcracker.store.entity.Address;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> getAddressesByCountryAndCityAndStreet(String country, String  city, String street, Pageable pageable);

    List<Address> getAddressesByCity(String city,Pageable pageable);

    Address getAddressByCountryAndCityAndStreetAndBuilding(String country,String city,String street,String building);
}
