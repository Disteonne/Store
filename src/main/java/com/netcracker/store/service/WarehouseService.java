package com.netcracker.store.service;

import com.netcracker.store.dto.WarehouseDeleteDto;
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

    public Product saveWarehouse(WarehousePatchDto warehousePostDto) {
        try {
            Product product = warehouseMapper.toProduct(warehousePostDto);
            productService.save(product);
            return product;
        } catch (InputException | AddressException | ProductException |SupplierException exception ) {
            exception.printStackTrace();
            return new Product();
        }
    }

    public Product editNewSupplier(WarehousePatchDto warehouse){
        try {
            return warehouseMapper.toProductNewSupplier(warehouse);
        }catch (SupplierException | AddressException |InputException exception){
            exception.printStackTrace();
            return new Product();
        }
    }

    public Product editEditSupplier(WarehousePatchDto warehouse){
        return warehouseMapper.toProductEditSupplier(warehouse);
    }

    public boolean delete(Long id){
        return productService.deleteById(id);
    }
}
