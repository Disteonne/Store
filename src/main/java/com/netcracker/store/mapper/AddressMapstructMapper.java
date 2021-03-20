package com.netcracker.store.mapper;

import com.netcracker.store.dto.AddressDto;
import com.netcracker.store.dto.UserDto;
import com.netcracker.store.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapstructMapper {

    AddressMapstructMapper ADDRESS_MAPSTRUCT_MAPPER= Mappers.getMapper(AddressMapstructMapper.class);

    AddressDto mapToAddressDto(Address address);

    List<AddressDto> mapToAddressDtoList(List<Address> addressList);

    @Mapping(source = "addressPostDto.country",target = "country")
    @Mapping(source = "addressPostDto.city",target = "city")
    @Mapping(source = "addressPostDto.street",target = "street")
    @Mapping(source = "addressPostDto.building",target = "building")
    Address mapToAddressPost(AddressDto addressPostDto);

    Address mapToAddressPut(AddressDto addressPutDto);

    //займись этим когда будет с USER
    Address mapToAddress(UserDto userProfilePatchDto);

}
