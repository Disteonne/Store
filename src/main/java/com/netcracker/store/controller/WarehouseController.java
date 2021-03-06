package com.netcracker.store.controller;

import com.netcracker.store.dto.WarehousePatchDto;
import com.netcracker.store.dto.WarehousePostDto;
import com.netcracker.store.entity.Product;
import com.netcracker.store.exception.AddressException;
import com.netcracker.store.exception.SupplierException;
import com.netcracker.store.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping("/warehouse/new")
    public WarehousePostDto save(@RequestBody WarehousePostDto warehouse) {
        return warehouseService.saveWarehouse(warehouse);
    }

    @PatchMapping("/warehouse/edit/newSupplier")
    public Product editNewSupplier(@RequestBody WarehousePatchDto warehouse) throws SupplierException, AddressException {
        return warehouseService.editNewSupplier(warehouse);
    }

    @PatchMapping("/warehouse/edit/editSupplier")
    public Product editEditSupplier(@RequestBody WarehousePatchDto warehouse){
        return warehouseService.editEditSupplier(warehouse);
    }
}
