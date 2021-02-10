package com.netcracker.store.service;

import com.netcracker.store.entity.Address;
import com.netcracker.store.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressService {

    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> getAllAddress() {
        return repository.findAll();
    }

    public Address getAddressById(int id) {
        return repository.getOne(id);
    }

    public Map<String, Boolean> deleteAddress(Address address) {
        repository.delete(address);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", true);
        return result;

    }

    public Map<String, Boolean> deleteAddressById(Integer id) {
        repository.deleteById(id);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", true);
        return result;
    }

    public Map<String, Boolean> saveAddress(Address address) {
        repository.save(address);
        Map<String, Boolean> result = new HashMap<>();
        result.put("saved", true);
        return result;
    }


}
