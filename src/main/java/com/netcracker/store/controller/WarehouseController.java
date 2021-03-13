package com.netcracker.store.controller;

import com.netcracker.store.dto.WarehouseNewSuppDto;
import com.netcracker.store.dto.WarehousePatchDto;
import com.netcracker.store.dto.WarehousePostDto;
import com.netcracker.store.entity.Product;
import com.netcracker.store.exception.ProductException;
import com.netcracker.store.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/warehouse/new")
    public Product save(@RequestBody WarehousePostDto warehouse) throws ProductException {
        return warehouseService.newProduct(warehouse);
    }

    @PatchMapping("/warehouse/edit/newSupplier")
    public Product editNewSupplier(@RequestBody WarehouseNewSuppDto warehouse) throws ProductException {
        return warehouseService.editNewSupplier(warehouse);
    }

    @PatchMapping("/warehouse/edit/editSupplier")
    public Product editEditSupplier(@RequestBody WarehousePatchDto warehouse) {
        return warehouseService.editEditSupplier(warehouse);
    }

    @DeleteMapping("/warehouse/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        return warehouseService.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
