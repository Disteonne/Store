package com.netcracker.store.service;

import com.netcracker.store.dto.WarehousePatchDto;
import com.netcracker.store.dto.WarehousePostDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.exception.AddressException;
import com.netcracker.store.exception.InputException;
import com.netcracker.store.exception.SupplierException;
import com.netcracker.store.exception.TypeNotFoundException;
import com.netcracker.store.mapper.AddressMapper;
import com.netcracker.store.mapper.SupplierMapper;
import com.netcracker.store.mapper.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseMapper warehouseMapper;
    private final AddressMapper addressMapper;
    private final SupplierMapper supplierMapper;

    private final AddressService addressService;
    private final SupplierService supplierService;
    private final ProductService productService;

    public WarehousePostDto saveWarehouse(WarehousePostDto warehousePostDto) {
        try {
            //part with address
            Address address = warehouseMapper.toAddress(warehousePostDto);
            if (existsAddress(address) == null) {
                addressService.save(address);//сохраняем новый
            } else {
                address = existsAddress(address);//присваиваем адрес.где есть id и тд
            }

            //part with supplier
            Supplier supplier = warehouseMapper.toSupplier(warehousePostDto, addressMapper.toAddressDto(address));
            if (existsSupplier(supplier) == null) {
                supplierService.save(supplier);//сохраняем нового
            } else {
                supplier = existsSupplier(supplier);//присваиваем поставщика.который уже имеется в бд
            }

            //part with product
            Product product = warehouseMapper.toProduct(warehousePostDto, supplierMapper.toSupplierDto(supplier));
            if (existsProduct(product) == null) {
                productService.save(product);
            } else {
                product = existsProduct(product);
            }

        } catch (InputException | TypeNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
        return warehousePostDto;
    }

    private Address existsAddress(Address newAddress) {
        return addressService.find(newAddress.getCountry(), newAddress.getCity(), newAddress.getStreet(), newAddress.getBuilding());
    }

    private Supplier existsSupplier(Supplier supplier) {
        return supplierService.getByAll(supplier.getName(), supplier.getMail(), supplier.getAddress());
    }

    private Product existsProduct(Product product) {
        return productService.getByName(product.getName());
    }

    public Product editNewSupplier(WarehousePatchDto warehouse) throws SupplierException, AddressException {
        return warehouseMapper.toProductNewSupplier(warehouse);
    }

    public Product editEditSupplier(WarehousePatchDto warehouse){
        return warehouseMapper.toProductEditSupplier(warehouse);
    }
}
