package com.netcracker.store.controller;

import com.netcracker.store.dto.SupplierDto;
import com.netcracker.store.dto.SupplierGetDto;
import com.netcracker.store.dto.SupplierPostDto;
import com.netcracker.store.dto.SupplierPutDto;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.mapper.SupplierMapper;
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

    @GetMapping("/supplier/{id}")
    public SupplierDto getById(@PathVariable(value = "id") Long id) {
        return supplierMapper.toSupplierDto(supplierService.getById(id));
    }

    @PostMapping("/supplier")
    public ResponseEntity<SupplierDto> save(@Valid @RequestBody SupplierPostDto supplierPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(supplierMapper.toSupplierDto(supplierService.save(supplierMapper.toSupplier(supplierPostDto))));
    }

    @DeleteMapping("/supplier/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id) {
        return supplierService.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/supplier")
    public ResponseEntity<SupplierDto> put(@Valid @RequestBody SupplierPutDto supplierPutDto) {
        return ResponseEntity.ok(supplierMapper.toSupplierDto(supplierService.save(supplierMapper.toSupplier(supplierPutDto))));
    }

    @PatchMapping("/supplier/{id}")
    public ResponseEntity<SupplierDto> patch(@PathVariable(name = "id") Long id, @RequestBody SupplierDto supplierDto) {
        Supplier supplier = supplierService.getById(id);
        return ResponseEntity
                .ok(supplierMapper.toSupplierDto(supplierService.save(supplierMapper.patch(supplier, supplierDto))));
    }

}
