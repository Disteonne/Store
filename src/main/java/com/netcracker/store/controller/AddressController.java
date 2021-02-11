package com.netcracker.store.controller;

import com.netcracker.store.entity.Address;
import com.netcracker.store.repository.AddressRepository;
import com.netcracker.store.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setAddressService(AddressService service) {
        this.addressService = service;
    }

    @GetMapping("/getAll")
    public List<Address> getAll() {
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable(value = "id") int id) {
        Address address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    @PostMapping("/saveAddress")
    public Map<String, Boolean> saveAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @DeleteMapping("/delete")
    public Map<String, Boolean> deleteAddress(@RequestBody Address address) {
        return addressService.deleteAddress(address);
    }

    @GetMapping("/delete/{id}")
    public Map<String, Boolean> deleteAddressById(@PathVariable(value = "id") int id) {
        return addressService.deleteAddressById(id);
    }


}
