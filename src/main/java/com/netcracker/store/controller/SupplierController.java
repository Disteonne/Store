package com.netcracker.store.controller;

import com.netcracker.store.dto.SupplierGetDto;
import com.netcracker.store.mapper.SupplierMapper;
import com.netcracker.store.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private SupplierMapper supplierMapper;

    @GetMapping("/suppliers") //
    public List<SupplierGetDto> getAll(@RequestParam(required = false) String nameLike,
                                       @RequestParam(required = false, defaultValue = "0") Integer page,
                                       @RequestParam(required = false, defaultValue = "10") Integer size,
                                       @RequestParam(required = false, defaultValue = "name") String sortName,
                                       @RequestParam(required = false, defaultValue = "asc") String orderBy) {

        return supplierMapper
                .toSupplierGetDtoList(supplierService
                        .getAll(nameLike, page, size, Sort.by(Sort.Direction.fromString(orderBy), sortName)));
    }
}
