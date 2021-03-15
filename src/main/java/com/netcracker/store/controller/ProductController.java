package com.netcracker.store.controller;

import com.netcracker.store.dto.*;
import com.netcracker.store.exception.TypeNotFoundException;
import com.netcracker.store.mapper.ProductMapper;
import com.netcracker.store.mapper.ProductMapstructMapper;
import com.netcracker.store.service.BasketService;
import com.netcracker.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private BasketService basketService;

    //+
    @GetMapping("/products")
    public List<ProductDto> getAll(@RequestParam(required = false) String type,
                                   @RequestParam(required = false) String nameLike,
                                   @RequestParam(required = false, defaultValue = "0") Integer page,
                                   @RequestParam(required = false, defaultValue = "5") Integer size,
                                   @RequestParam(required = false, defaultValue = "name") String sortName,
                                   @RequestParam(required = false, defaultValue = "asc") String orderBy) {
        return ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.toProductDtoList(productService.
                getAll(type, nameLike, page, size, Sort.by(Sort.Direction.fromString(orderBy), sortName)));
        /*

        return productMapper
                .toProductDtoList(productService.
                        getAll(type, nameLike, page, size, Sort.by(Sort.Direction.fromString(orderBy), sortName)));
         */
    }

    //+
    @GetMapping("/all/products")
    public List<ProductDto> getAllProduct() {
        return ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.toProductDtoList(productService.getAll());
        //return productMapper.toProductDtoList(productService.getAll());
    }

    @GetMapping("/product/{id}")
    public ProductDto getById(@PathVariable(value = "id") Long id) {
        return ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.mapToProductDto(productService.getById(id));
        //return productMapper.toProductDto(productService.getById(id));
    }

    //+
    @GetMapping("/count")
    public Integer getCount(@RequestParam(required = false, value = "name") String name) {
        if (name == null) {
            return productService.getCountAll();
        } else
            return productService.getCountByName(name);
    }

    @GetMapping("/product")
    public ProductDto getByName(@RequestParam(value = "name") String name) {
        return ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.mapToProductDto(productService.getByName(name));
        //return productMapper.toProductDto(productService.getByName(name));
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> save(@Valid @RequestBody ProductPostDto productPostDto) throws TypeNotFoundException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.mapToProductDto(
                        productService.save(ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.mapToProduct(productPostDto))));
        /*
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productMapper.toProductDto(productService.save(productMapper.toProduct(productPostDto))));
         */
    }

    @PostMapping("/basket")
    public ResponseEntity<Void> basket(@RequestBody List<BasketDto> basket) {
        return basketService.addHistory(basket) ? ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @PutMapping("/product")
    public ResponseEntity<ProductDto> put(@Valid @RequestBody ProductPutDto productPutDto) {
        return ResponseEntity
                .ok(ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.mapToProductDto(
                        productService.save(ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.mapToProduct((productPutDto)))));
        /*
        return ResponseEntity
                .ok(productMapper.toProductDto(productService.save(productMapper.toProduct(productPutDto))));
         */
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        return productService.deleteById(id) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
