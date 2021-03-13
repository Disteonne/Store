package com.netcracker.store.service;

import com.netcracker.store.dto.WarehouseDeleteDto;
import com.netcracker.store.dto.WarehouseNewSuppDto;
import com.netcracker.store.dto.WarehousePatchDto;
import com.netcracker.store.dto.WarehousePostDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.exception.*;
import com.netcracker.store.mapper.AddressMapper;
import com.netcracker.store.mapper.SupplierMapper;
import com.netcracker.store.mapper.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class WarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private AddressService addressService;

    public Product newProduct(WarehousePostDto warehousePostDto) throws ProductException {
        Product product = warehouseMapper.toProduct(warehousePostDto);
        inputData(product);
        if (productService.getByName(product.getName()) != null) {
            throw new ProductException("Product in db already");
        }
        return productService.save(product);
    }

    public Product editNewSupplier(WarehouseNewSuppDto warehouse) throws ProductException {
        Product product = warehouseMapper.toProductNewSupplier(warehouse);
        inputData(product);
        return productService.save(product);
    }

    public Product editEditSupplier(WarehousePatchDto warehouse) {
        return warehouseMapper.toProductEditSupplier(warehouse);
    }

    public boolean delete(Long id) {
        return productService.deleteById(id);
    }
    //boolean
    protected Address isExists(Address address) {
        Address search = addressService.find(address.getCountry(), address.getCity(), address.getStreet(), address.getBuilding());
        return search == null ? addressService.save(address) : search;
    }

    protected Supplier isExists(Supplier supplier) {
        Supplier search = supplierService.getByAll(supplier.getName(), supplier.getMail(), supplier.getAddress());
        return search == null ? supplierService.save(supplier) : search;
    }

    protected void inputData(Product product) {
        Supplier supplier = product.getSupplier();
        supplier.setAddress(isExists(supplier.getAddress()));
        product.setSupplier(isExists(supplier));
    }
}
