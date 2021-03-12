package com.netcracker.store.mapper;

import com.netcracker.store.dto.AddressDto;
import com.netcracker.store.dto.AddressPostDto;
import com.netcracker.store.dto.AddressPutDto;
import com.netcracker.store.entity.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapstructMapper {

    AddressDto mapToAddressDto(Address address);

    AddressPostDto mapToAddressPostDto(Address address);

    List<AddressDto> mapToAddressDtoList(List<Address> addressList);

    Address mapToAddress(AddressPostDto addressPostDto);

    Address mapToAddress(AddressPutDto addressPutDto);
}
