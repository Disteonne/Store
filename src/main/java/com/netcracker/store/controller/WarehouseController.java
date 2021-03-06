package com.netcracker.store.controller;

import com.netcracker.store.dto.WarehousePostDto;
import com.netcracker.store.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping("/warehouse")
    public WarehousePostDto save(@RequestBody WarehousePostDto warehouse) {
        return warehouseService.saveWarehouse(warehouse);
    }
}
