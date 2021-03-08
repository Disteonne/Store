package com.netcracker.store.controller;

import com.netcracker.store.dto.AddressDto;
import com.netcracker.store.dto.AddressPostDto;
import com.netcracker.store.dto.AddressPutDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.mapper.AddressMapper;
import com.netcracker.store.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;
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


    @GetMapping("/address/{id}")
    public AddressDto getById(@PathVariable(value = "id") Long id) {
        return addressMapper.toAddressDto(addressService.getById(id));
    }

    @PostMapping("/address")
    public ResponseEntity<AddressDto> save(@Valid @RequestBody AddressPostDto addressPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressMapper.toAddressDto(addressService.save(addressMapper.toAddress(addressPostDto))));
    }

    @DeleteMapping("/address/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        return addressService.deleteById(id) ?
                ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/address")
    public ResponseEntity<AddressDto> put(@Valid @RequestBody AddressPutDto addressPutDto) {
        return ResponseEntity
                .ok(addressMapper.toAddressDto(addressService.save(addressMapper.toAddress(addressPutDto))));
    }

    @PatchMapping("/address/{id}")
    public ResponseEntity<AddressDto> patch(@PathVariable(name = "id") Long id, @Valid @RequestBody AddressDto addressDto) {
        Address address = addressService.getById(id);
        return ResponseEntity.ok(addressMapper.toAddressDto(addressService.save(addressMapper.patch(address, addressDto))));
    }

}
