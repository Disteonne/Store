package com.netcracker.store.repository;

import com.netcracker.store.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}

//UI