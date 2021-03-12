package com.netcracker.store.mapper;

import com.netcracker.store.dto.AddressDto;
import com.netcracker.store.dto.AddressPostDto;
import com.netcracker.store.dto.AddressPutDto;
import com.netcracker.store.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapstructMapper {

    AddressMapstructMapper ADDRESS_MAPSTRUCT_MAPPER= Mappers.getMapper(AddressMapstructMapper.class);

    AddressDto mapToAddressDto(Address address);

    AddressPostDto mapToAddressPostDto(Address address);

    List<AddressDto> mapToAddressDtoList(List<Address> addressList);

    Address mapToAddress(AddressPostDto addressPostDto);

    Address mapToAddress(AddressPutDto addressPutDto);

    default Address mapToAddress(Address address, AddressDto addressDto) {
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
