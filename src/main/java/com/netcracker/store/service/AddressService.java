package com.netcracker.store.service;

import com.netcracker.store.entity.Address;
import com.netcracker.store.exeption.NotFoundException;
import com.netcracker.store.repository.AddressRepository;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Address> getAddressById(int id) throws NotFoundException {
        return ResponseEntity.ok().body(find(id));
    }

    public Map<String, Boolean> deleteAddress(Address address) {
        repository.delete(address);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", true);
        return result;

    }

    public Map<String, Boolean> deleteAddressById(Integer id) throws NotFoundException {
        Address address= find(id);
        repository.delete(address);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", Boolean.TRUE);
        return result;
    }

    public Map<String, Boolean> saveAddress(Address address) {
        Map<String, Boolean> result = new HashMap<>();
            repository.save(address);
            result.put("saved", true);
        return result;
    }

    public Map<String,Boolean> updateFullAddress(String country,String city,String street,String building,int id) throws NotFoundException {
        Address address=find(id);
        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setBuilding(building);
        repository.save(address);
        Map<String,Boolean> result=new HashMap<>();
        result.put("Address full update",Boolean.TRUE);
        return result;
    }

    private Address find(int id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Address not found"));
    }


}
