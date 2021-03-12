package com.netcracker.store.controller;

import com.netcracker.store.dto.AddressDto;
import com.netcracker.store.mapper.AddressMapper;
import com.netcracker.store.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressMapper addressMapper;

    //пагинация дял селектора
    @GetMapping("/addresses")
    public List<AddressDto> getAll(@RequestParam(required = false) String country,
                                   @RequestParam(required = false) String city,
                                   @RequestParam(required = false) String street,
                                   @RequestParam(required = false, defaultValue = "0") Integer page,
                                   @RequestParam(required = false, defaultValue = "10") Integer size,
                                   @RequestParam(required = false, defaultValue = "street") String sortName,
                                   @RequestParam(required = false, defaultValue = "asc") String orderBy) {
        return addressMapper
                .toAddressDtoList(addressService
                        .getAll(country, city, street, page, size, Sort.by(Sort.Direction.fromString(orderBy), sortName)));
    }

}
