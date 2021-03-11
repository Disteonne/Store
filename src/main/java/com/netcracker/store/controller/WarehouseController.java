package com.netcracker.store.controller;

import com.netcracker.store.dto.WarehouseDeleteDto;
import com.netcracker.store.dto.WarehousePatchDto;
import com.netcracker.store.entity.Product;
import com.netcracker.store.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PatchMapping("/warehouse/new")
    public Product save(@RequestBody WarehousePatchDto warehouse) {
        return warehouseService.saveWarehouse(warehouse);
    }

    @PatchMapping("/warehouse/edit/newSupplier")
    public Product editNewSupplier(@RequestBody WarehousePatchDto warehouse) {
        return warehouseService.editNewSupplier(warehouse);
    }

    @PatchMapping("/warehouse/edit/editSupplier")
    public Product editEditSupplier(@RequestBody WarehousePatchDto warehouse) {
        return warehouseService.editEditSupplier(warehouse);
    }

    @DeleteMapping("/warehouse/delete")
    public ResponseEntity<Void> delete(@RequestBody WarehouseDeleteDto warehouse) {
        return warehouseService.delete(warehouse) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
