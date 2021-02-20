package com.netcracker.store.controller;

import com.netcracker.store.dto.ProductDto;
import com.netcracker.store.dto.ProductPostDto;
import com.netcracker.store.dto.ProductPutDto;
import com.netcracker.store.entity.Product;
import com.netcracker.store.mapper.ProductMapper;
import com.netcracker.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/products")
    public List<ProductDto> getAll(@RequestParam(required = false) String type,
                                   @RequestParam(required = false) String nameLike,
                                   @RequestParam(required = false, defaultValue = "0") Integer page,
                                   @RequestParam(required = false, defaultValue = "6") Integer size,
                                   @RequestParam(required = false, defaultValue = "name") String sortName,
                                   @RequestParam(required = false, defaultValue = "asc") String orderBy) {
        return productMapper
                .toProductDtoList(productService.
                        getAll(type, nameLike, page, size, Sort.by(Sort.Direction.fromString(orderBy), sortName)));
    }

    @GetMapping("/product/{id}")
    public ProductDto getById(@PathVariable(value = "id") Long id) {
        return productMapper.toProductDto(productService.getById(id));
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> save(@Valid @RequestBody ProductPostDto productPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productMapper.toProductDto(productService.save(productMapper.toProduct(productPostDto))));
    }

    @DeleteMapping("/product/delete{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        return productService.deleteById(id) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/product")
    public ResponseEntity<ProductDto> put(@Valid @RequestBody ProductPutDto productPutDto) {
        return ResponseEntity
                .ok(productMapper.toProductDto(productService.save(productMapper.toProduct(productPutDto))));
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<Void> patch(@PathVariable(value = "id") Long id, @Valid @RequestBody ProductDto dto) {
        Product product = productService.getById(id);
        productService.save(productMapper.patch(product, dto));
        return ResponseEntity.ok().build();
    }
}
