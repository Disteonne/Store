package com.netcracker.store.controller;

import com.netcracker.store.dto.AddressDto;
import com.netcracker.store.dto.AddressPostDto;
import com.netcracker.store.dto.AddressPutDto;
import com.netcracker.store.mapper.AddressMapper;
import com.netcracker.store.mapper.AddressMapstructMapper;
import com.netcracker.store.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    //пагинация дял селектора
    @GetMapping("/addresses")
    public List<AddressDto> getAll(@RequestParam(required = false) String country,
                                   @RequestParam(required = false) String city,
                                   @RequestParam(required = false) String street,
                                   @RequestParam(required = false, defaultValue = "0") Integer page,
                                   @RequestParam(required = false, defaultValue = "10") Integer size,
                                   @RequestParam(required = false, defaultValue = "street") String sortName,
                                   @RequestParam(required = false, defaultValue = "asc") String orderBy) {
        return AddressMapstructMapper.ADDRESS_MAPSTRUCT_MAPPER.mapToAddressDtoList(addressService
                        .getAll(country, city, street, page, size, Sort.by(Sort.Direction.fromString(orderBy), sortName)));
    }

    @GetMapping("/address")
    public List<AddressDto> getAll(){
        return AddressMapstructMapper.ADDRESS_MAPSTRUCT_MAPPER.mapToAddressDtoList(addressService.getAll());
    }

    @GetMapping("/address/{id}")
    public AddressDto getById(@PathVariable(value = "id") Long id) {
        return AddressMapstructMapper.ADDRESS_MAPSTRUCT_MAPPER.mapToAddressDto(addressService.getById(id));
    }

    /*
    getAddress(...)-проверяет наличие адреса в бд,если адрес существует,то возвращает его
     */
    @PostMapping("/address")
    public ResponseEntity<AddressDto> save(@Valid @RequestBody AddressPostDto addressPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AddressMapstructMapper.ADDRESS_MAPSTRUCT_MAPPER.mapToAddressDto(addressService.save(addressService.
                        getAddress(AddressMapstructMapper.ADDRESS_MAPSTRUCT_MAPPER.mapToAddress(addressPostDto)))));
    }

    @PutMapping("/address")
    public ResponseEntity<AddressDto> put(@Valid @RequestBody AddressPutDto addressPutDto) {
        return ResponseEntity
                .ok(AddressMapstructMapper.ADDRESS_MAPSTRUCT_MAPPER.mapToAddressDto
                        (addressService.save(AddressMapstructMapper.ADDRESS_MAPSTRUCT_MAPPER.mapToAddress(addressPutDto))));
    }
}
