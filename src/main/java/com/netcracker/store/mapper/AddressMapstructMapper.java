package com.netcracker.store.mapper;

import com.netcracker.store.dto.AddressDto;
import com.netcracker.store.dto.AddressPostDto;
import com.netcracker.store.dto.AddressPutDto;
import com.netcracker.store.dto.UserProfilePatchDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.repository.AddressRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapstructMapper {

    AddressMapstructMapper ADDRESS_MAPSTRUCT_MAPPER= Mappers.getMapper(AddressMapstructMapper.class);

    AddressDto mapToAddressDto(Address address);

    AddressPostDto mapToAddressPostDto(Address address);

    List<AddressDto> mapToAddressDtoList(List<Address> addressList);

    Address mapToAddress(AddressPostDto addressPostDto);

    Address mapToAddress(AddressPutDto addressPutDto);

    Address mapToAddress(UserProfilePatchDto userProfilePatchDto);

}
