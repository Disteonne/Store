package com.netcracker.store.controller;

import com.netcracker.store.check.CheckForPatchMapping;
import com.netcracker.store.dto.UserDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.exeption.NotFoundException;
import com.netcracker.store.exeption.ResponseInputException;
import com.netcracker.store.repository.AddressRepository;
import com.netcracker.store.service.AddressService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.PathParam;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;


    private final CheckForPatchMapping checkForPatchMapping= new CheckForPatchMapping();

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
    public ResponseEntity<Address> getAddressById(@PathVariable(value = "id") int id) throws NotFoundException {
        return addressService.getAddressById(id);
    }

    @PostMapping("/saveAddress")
    public Map<String, Boolean> saveAddress(@Valid @RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @DeleteMapping("/delete")
    public Map<String, Boolean> deleteAddress(@Valid @RequestBody Address address) {
        return addressService.deleteAddress(address);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteAddressById(@PathVariable(value = "id") int id) throws NotFoundException {
        return addressService.deleteAddressById(id);
    }

    @PutMapping("/update/put")
    public ResponseEntity<String> putUpdate(@RequestBody Address address) {
        addressService.saveAddress(address);
        return ResponseEntity.ok("Updated");
    }

    @PatchMapping("/update/patch")
    public ResponseEntity<String> patchUpdate(@RequestBody Address address) throws IllegalAccessException, NotFoundException, ResponseInputException {
        Map<String,String> fields= checkForPatchMapping.validateObject(address);
        for (Map.Entry<String, String> pair : fields.entrySet()
        ) {
            addressService.updatePartAddress(pair.getKey(), pair.getValue(), address.getId());
        }
        return ResponseEntity.ok("Updated");
    }

    @PatchMapping("/update/country={country}&city={city}&street={street}&building={building}&id={id}")
    public Map<String, Boolean> updateAddress(@Valid @PathVariable(value = "country") String country,
                                              @Valid @PathVariable(value = "city") String city,
                                              @Valid @PathVariable(value = "street") String street,
                                              @Valid @PathVariable(value = "building") String building,
                                              @Valid @PathVariable(value = "id") int id) throws NotFoundException {
        return addressService.updateFullAddress(country, city, street, building, id);
    }

    @PatchMapping("/update/update={field}&to={info}&id={id}")
    public Map<String, Boolean> updateAddressPart(@Valid @PathVariable(value = "field") String field,
                                                  @Valid @PathVariable(value = "info") String info,
                                                  @Valid @PathVariable(value = "id") int id) throws NotFoundException, ResponseInputException {
        return addressService.updatePartAddress(field, info, id);
    }

}
