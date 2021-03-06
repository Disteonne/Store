package com.netcracker.store.mapper;

import com.netcracker.store.dto.*;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.exception.InputException;
import com.netcracker.store.exception.TypeNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class WarehouseMapper {

    private final ProductMapper productMapper;
    private final AddressMapper addressMapper;
    private final SupplierMapper supplierMapper;

    public Address toAddress(WarehousePostDto warehouse) throws InputException {
        AddressPostDto addressPostDto = new AddressPostDto();
        if (warehouse != null) {
            addressPostDto.setCountry(warehouse.getCountry());
            addressPostDto.setCity(warehouse.getCity());
            addressPostDto.setStreet(warehouse.getStreet());
            addressPostDto.setBuilding(warehouse.getBuilding());
        } else {
            throw new InputException("Input exception: WarehouseMapper-> toAddress");
        }
        return addressMapper.toAddress(addressPostDto);
    }

    public Supplier toSupplier(WarehousePostDto warehouse, AddressDto addressDto) throws InputException {
        SupplierPostDto supplierPostDto = new SupplierPostDto();
        if (warehouse != null && addressDto != null) {
            supplierPostDto.setName(warehouse.getSupplierName());
            supplierPostDto.setMail(warehouse.getMail());
            supplierPostDto.setAddressId(addressDto.getId());
        } else {
            throw new InputException("Input exception: WarehouseMapper-> toSupplier");
        }
        return supplierMapper.toSupplier(supplierPostDto);
    }

    public Product toProduct(WarehousePostDto warehouse, SupplierDto supplierDto) throws TypeNotFoundException, InputException {
        ProductPostDto productPostDto = new ProductPostDto();
        if (warehouse != null && supplierDto != null) {
            productPostDto.setName(warehouse.getProductName());
            productPostDto.setPrice(warehouse.getPrice());
            productPostDto.setCount(warehouse.getCount());
            productPostDto.setType(warehouse.getType());
            productPostDto.setInfo(warehouse.getInfo());
            productPostDto.setSupplierId(supplierDto.getId());
        }else {
            throw new InputException("Input exception: WarehouseMapper-> toProduct");
        }
        return productMapper.toProduct(productPostDto);
    }


}
