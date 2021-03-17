package com.netcracker.store.service;


import com.netcracker.store.entity.Address;
import com.netcracker.store.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address getById(Long id) {
        return addressRepository.getOne(id);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public boolean deleteById(Long id) {
        try {
            addressRepository.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public Address getAddress(Address address) {
        Address searchFromDb = addressRepository.getAddressByCountryAndCityAndStreetAndBuilding(address.getCountry(),
                address.getCity(), address.getStreet(), address.getBuilding());
        return searchFromDb == null ? address : searchFromDb;
    }


    public List<Address> getAll(String country, String city, String street, int page, int size, Sort sort){
       return addressRepository.findByQuery(country,city,street,PageRequest.of(page, size, sort));
    }

    public Address find(String country, String city, String street, String building) {
        return addressRepository.getAddressByCountryAndCityAndStreetAndBuilding(country, city, street, building);
    }

}
