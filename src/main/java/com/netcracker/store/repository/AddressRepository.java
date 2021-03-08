package com.netcracker.store.repository;

import com.netcracker.store.entity.Address;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> getAddressesByCountry(String country,Pageable pageable);

    List<Address> getAddressesByCity(String city,Pageable pageable);

    List<Address> getAddressesByStreet(String street,Pageable pageable);

    List<Address> getAddressesByCountryAndCity(String country,String city,Pageable pageable);

    List<Address> getAddressesByCountryAndStreet(String country,String street,Pageable pageable);

    List<Address> getAddressesByCityAndStreet(String city,String street,Pageable pageable);

    List<Address> getAddressesByCountryAndCityAndStreet(String country, String  city, String street, Pageable pageable);

    //for search db
    Address getAddressByCountryAndCityAndStreetAndBuilding(String country,String city,String street,String building);
}
