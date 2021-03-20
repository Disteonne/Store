package com.netcracker.store.mapper;

import com.netcracker.store.dto.SupplierDto;
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
    @Mapping(source = "supplier.address.country",target = "country")
    @Mapping(source = "supplier.address.city",target = "city")
    @Mapping(source = "supplier.address.street",target = "street")
    @Mapping(source = "supplier.address.building",target = "building")
    SupplierDto mapToSupplierDto(Supplier supplier);

    @Mapping(source = "supplierPostDto.addressId",target = "address.id")
    Supplier mapToSupplierPost(SupplierDto supplierPostDto);

    @Mapping(source = "supplierPutDto.addressId",target = "address.id")
    Supplier mapToSupplierPut(SupplierDto supplierPutDto);

    default List<SupplierDto> mapToSupplierDtoList(List<Supplier> all) {
        List<SupplierDto> result=new ArrayList<>();
        if(all==null){
            return result;
        }
        all.forEach(supplier -> result.add(mapToSupplierDto(supplier)));
        return result;
    }

    default List<SupplierDto> toSupplierGetDtoList(List<Supplier> suppliers){
        List<SupplierDto> result=new ArrayList<>();
        if(suppliers==null){
            return result;
        }
        suppliers.forEach(supplier -> result.add(mapToSupplierDto(supplier)));
        return result;
    }

}
