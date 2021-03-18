package com.netcracker.store.controller;

import com.netcracker.store.dto.SupplierDto;
import com.netcracker.store.dto.SupplierGetDto;
import com.netcracker.store.dto.SupplierPostDto;
import com.netcracker.store.dto.SupplierPutDto;
import com.netcracker.store.mapper.SupplierMapper;
import com.netcracker.store.mapper.SupplierMapstructMapper;
import com.netcracker.store.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/suppliers") //
    public List<SupplierGetDto> getAll(@RequestParam(required = false) String nameLike,
                                       @RequestParam(required = false, defaultValue = "0") Integer page,
                                       @RequestParam(required = false, defaultValue = "10") Integer size,
                                       @RequestParam(required = false, defaultValue = "name") String sortName,
                                       @RequestParam(required = false, defaultValue = "asc") String orderBy) {

        return SupplierMapstructMapper.SUPPLIER_MAPSTRUCT_MAPPER.toSupplierGetDtoList(supplierService
                .getAll(nameLike, page, size, Sort.by(Sort.Direction.fromString(orderBy), sortName)));
    }

    @GetMapping("/allSuppliers")
    public List<SupplierDto> getAllWithoutPaginationAndSort() {
        return SupplierMapstructMapper.SUPPLIER_MAPSTRUCT_MAPPER.mapToSupplierDtoList(supplierService.getAll());
    }

    @GetMapping("/supplier/{id}")
    public SupplierDto getById(@PathVariable(value = "id") Long id) {
        return SupplierMapstructMapper.SUPPLIER_MAPSTRUCT_MAPPER.mapToSupplierDto(supplierService.getById(id));
    }

    /*
        getSupplier() проверяет есть ли в бд такой поставщик ( по наименованию), т.к  1 поставщик-1 адрес
        1 адрес-много поставщиков
        (в бд указывается поставщик с уникальным названием компании.поэтому ищем по имени)
     */
    @PostMapping("/supplier")
    public ResponseEntity<SupplierDto> save(@Valid @RequestBody SupplierPostDto supplierPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SupplierMapstructMapper.SUPPLIER_MAPSTRUCT_MAPPER.mapToSupplierDto
                        (supplierService.save(supplierService.
                                getSupplier(SupplierMapstructMapper.
                                        SUPPLIER_MAPSTRUCT_MAPPER.mapToSupplier(supplierPostDto)))));
    }

    @PutMapping("/supplier")
    public ResponseEntity<SupplierDto> put(@Valid @RequestBody SupplierPutDto supplierPutDto) {
        return ResponseEntity.ok(SupplierMapstructMapper.SUPPLIER_MAPSTRUCT_MAPPER.mapToSupplierDto(
                supplierService.save(SupplierMapstructMapper.SUPPLIER_MAPSTRUCT_MAPPER.mapToSupplier(supplierPutDto))));
    }
}
