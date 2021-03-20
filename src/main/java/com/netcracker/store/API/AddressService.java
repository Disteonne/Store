package com.netcracker.store.API;

import com.netcracker.store.entity.Address;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    List<Address> getAll();

    Address getById(Long id);

    Address save(Address address);

    Address getAddress(Address address);

    List<Address> getAll(String country, String city, String street, int page, int size, Sort sort);

    Address find(String country, String city, String street, String building);
}
