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

    public List<Address> getAll(String country, String city, String street, int page, int size, Sort sort) {
        if(country!=null && city==null && street==null){
            return addressRepository.getAddressesByCountry(country,PageRequest.of(page, size, sort));
        }
        if(country==null && city!=null && street==null){
            return addressRepository.getAddressesByCity(city, PageRequest.of(page, size, sort));
        }
        if(country==null && city==null && street!=null){
            return addressRepository.getAddressesByStreet(street,PageRequest.of(page,size,sort));
        }
        if(country!=null && city!=null && street==null){
            return  addressRepository.getAddressesByCountryAndCity(country,city,PageRequest.of(page,size,sort));
        }
        if(country!=null && city==null && street!=null){
            return addressRepository.getAddressesByCountryAndStreet(country,street,PageRequest.of(page,size,sort));
        }
        if(country==null && city!=null && street!=null){
            return  addressRepository.getAddressesByCityAndStreet(city,street,PageRequest.of(page,size,sort));
        }
        if(country!=null && city!=null && street!=null){
            return  addressRepository.getAddressesByCountryAndCityAndStreet(country,city,street,PageRequest.of(page,size,sort));
        }
        return addressRepository.findAll(PageRequest.of(page,size,sort)).getContent();
    }

    public Address find(String country, String city, String street, String building) {
        return addressRepository.getAddressByCountryAndCityAndStreetAndBuilding(country, city, street, building);
    }


}
