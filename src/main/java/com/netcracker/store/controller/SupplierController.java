package com.netcracker.store.controller;

import com.netcracker.store.dto.SupplierDto;
import com.netcracker.store.dto.SupplierPostDto;
import com.netcracker.store.dto.SupplierPutDto;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.mapper.SupplierMapper;
import com.netcracker.store.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    @GetMapping("/suppliers") //
    public List<SupplierDto> getAll(@RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer size,
                                    @RequestParam(required = false) String sortName,
                                    @RequestParam(required = false) String orderBy) {

        //рассмотри пагинацию отдельно от сортировки ???
        // default
        if (sortName == null) {
            return supplierMapper.toSupplierDtoList(supplierService.getAll());
        }
        return supplierMapper
                .toSupplierDtoList(supplierService
                        .getAll(page, size, Sort.by(Sort.Direction.fromString(orderBy), sortName)));
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
