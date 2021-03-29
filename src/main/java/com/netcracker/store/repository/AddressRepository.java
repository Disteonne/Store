package com.netcracker.store.repository;

import com.netcracker.store.entity.Address;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    @Query(value = "SELECT * FROM Address a where a.country LIKE  CONCAT('%',:country,'%') AND a.city LIKE CONCAT('%',:city,'%') AND a.street LIKE CONCAT('%',:street,'%')",nativeQuery = true)
    List<Address> findByQuery(@Param("country") String country,@Param("city") String city,@Param("street") String street,Pageable pageable);

    //for search db
    Address getAddressByCountryAndCityAndStreetAndBuilding(String country,String city,String street,String building);
}
