package com.netcracker.store.service;

import com.netcracker.store.entity.Address;
import com.netcracker.store.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> getAllAddress(){
        return repository.findAll();
    }

    public Address getAddressById(int id){
        return repository.getOne(id);
    }
}
