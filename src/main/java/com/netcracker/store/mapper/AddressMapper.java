package com.netcracker.store.mapper;

import com.netcracker.store.dto.AddressDto;
import com.netcracker.store.dto.AddressPostDto;
import com.netcracker.store.dto.AddressPutDto;
import com.netcracker.store.entity.Address;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
//выполнен полностью mapstruct кроме одного метода patch
@Component
public class AddressMapper {

    public AddressDto toAddressDto(Address address) {
        if (address == null) {
            return null;
        }
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setCountry(address.getCountry());
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        if (address.getBuilding() != null) {
            addressDto.setBuilding(address.getBuilding());
        }
        return addressDto;
    }
    public AddressPostDto toAddressPostDto(AddressDto addressDto){
        AddressPostDto addressPostDto = new AddressPostDto();
        addressPostDto.setCountry(addressDto.getCountry());
        addressPostDto.setCity(addressDto.getCity());
        addressPostDto.setStreet(addressDto.getStreet());
        addressPostDto.setBuilding(addressDto.getBuilding());
        return addressPostDto;
    }

    public List<AddressDto> toAddressDtoList(List<Address> all) {
        List<AddressDto> result = new ArrayList<>();
        if (all == null) {
            return result;
        }
        all.forEach(address -> result.add(toAddressDto(address)));
        return result;
    }

    public Address toAddress(AddressPostDto addressPostDto) {
        if (addressPostDto == null) {
            return null;
        }
        Address address=new Address();
        address.setCountry(addressPostDto.getCountry());
        address.setCity(addressPostDto.getCity());
        address.setStreet(addressPostDto.getStreet());
        if(addressPostDto.getBuilding()!=null){
            address.setBuilding(addressPostDto.getBuilding());
        }
        return address;
    }

    public Address toAddress(AddressPutDto addressPutDto){
        if (addressPutDto == null) {
            return null;
        }
        Address address=new Address();
        address.setId(addressPutDto.getId());
        address.setCountry(addressPutDto.getCountry());
        address.setCity(addressPutDto.getCity());
        address.setStreet(addressPutDto.getStreet());
        if(addressPutDto.getBuilding()!=null){
            address.setBuilding(addressPutDto.getBuilding());
        }
        return address;
    }

    //перенеси на service
    public Address patch(Address address, AddressDto addressDto) {
        if(addressDto==null){
            return address;
        }
        if(!addressDto.getCountry().equals("")){
            address.setCountry(addressDto.getCountry());
        }
        if(!addressDto.getCity().equals("")){
            address.setCity(addressDto.getCity());
        }
        if(!addressDto.getStreet().equals("")){
            address.setStreet(addressDto.getStreet());
        }
        if(!addressDto.getBuilding().equals("")){
            address.setBuilding(addressDto.getBuilding());
        }
        return address;
    }

}
