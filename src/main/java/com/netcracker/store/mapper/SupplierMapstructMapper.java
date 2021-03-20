package com.netcracker.store.mapper;

import com.netcracker.store.dto.SupplierDto;
import com.netcracker.store.dto.SupplierGetDto;
import com.netcracker.store.dto.SupplierPostDto;
import com.netcracker.store.dto.SupplierPutDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SupplierMapstructMapper {

    SupplierMapstructMapper SUPPLIER_MAPSTRUCT_MAPPER= Mappers.getMapper(SupplierMapstructMapper.class);

    @Mapping(source = "supplier.address.id",target = "addressId")
    SupplierDto mapToSupplierDto(Supplier supplier);

    @Mapping(source = "supplierPostDto.addressId",target = "address.id")
    Supplier mapToSupplier(SupplierPostDto supplierPostDto);

    @Mapping(source = "supplierPutDto.addressId",target = "address.id")
    Supplier mapToSupplier(SupplierPutDto supplierPutDto);

    @Mapping(source = "supplier.address.country",target = "country")
    @Mapping(source = "supplier.address.city",target = "city")
    @Mapping(source = "supplier.address.street",target = "street")
    @Mapping(source = "supplier.address.building",target = "building")
    SupplierGetDto mapToSupplierGetDto(Supplier supplier);

    default List<SupplierDto> mapToSupplierDtoList(List<Supplier> all) {
        List<SupplierDto> result=new ArrayList<>();
        if(all==null){
            return result;
        }
        all.forEach(supplier -> result.add(mapToSupplierDto(supplier)));
        return result;
    }

    default List<SupplierGetDto> toSupplierGetDtoList(List<Supplier> suppliers){
        List<SupplierGetDto> result=new ArrayList<>();
        if(suppliers==null){
            return result;
        }
        suppliers.forEach(supplier -> result.add(mapToSupplierGetDto(supplier)));
        return result;
    }

}
