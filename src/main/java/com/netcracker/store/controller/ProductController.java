package com.netcracker.store.controller;

import com.netcracker.store.API.BasketService;
import com.netcracker.store.API.ProductService;
import com.netcracker.store.dto.*;
import com.netcracker.store.exception.ProductException;
import com.netcracker.store.exception.TypeNotFoundException;
import com.netcracker.store.mapper.ProductMapstructMapper;
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
    private ProductService productServiceImpl;
    @Autowired
    private BasketService basketServiceImpl;

    //+
    @GetMapping("/products")
    public List<ProductDto> getAll(@RequestParam(required = false) String type,
                                   @RequestParam(required = false) String nameLike,
                                   @RequestParam(required = false, defaultValue = "0") Integer page,
                                   @RequestParam(required = false, defaultValue = "5") Integer size,
                                   @RequestParam(required = false, defaultValue = "name") String sortName,
                                   @RequestParam(required = false, defaultValue = "asc") String orderBy) {
        return ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.toProductDtoList(productServiceImpl.
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
        return ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.toProductDtoList(productServiceImpl.getAll());
        //return productMapper.toProductDtoList(productService.getAll());
    }

    @GetMapping("/product/{id}")
    public ProductDto getById(@PathVariable(value = "id") Long id) {
        return ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.mapToProductDto(productServiceImpl.getById(id));
        //return productMapper.toProductDto(productService.getById(id));
    }

    //+
    @GetMapping("/count")
    public Integer getCount(@RequestParam(required = false, value = "name") String name) {
        if (name == null) {
            return productServiceImpl.getCountAll();
        } else
            return productServiceImpl.getCountByName(name);
    }

    @GetMapping("/product")
    public ProductDto getByName(@RequestParam(value = "name") String name) {
        return ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.mapToProductDto(productServiceImpl.getByName(name));
        //return productMapper.toProductDto(productService.getByName(name));
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> save(@Valid @RequestBody ProductDto productPostDto) throws TypeNotFoundException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.mapToProductDto(
                        productServiceImpl.save(ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.mapToProductPost(productPostDto))));
        /*
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productMapper.toProductDto(productService.save(productMapper.toProduct(productPostDto))));
         */
    }

    @PostMapping("/basket")
    public ResponseEntity<Void> basket(@RequestBody List<BasketDto> basket) {
        return basketServiceImpl.addHistory(basket) ? ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @PutMapping("/product")
    public ResponseEntity<ProductDto> put(@Valid @RequestBody ProductDto productPutDto) {
        return ResponseEntity
                .ok(ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.mapToProductDto(
                        productServiceImpl.save(ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.mapToProductPut((productPutDto)))));
        /*
        return ResponseEntity
                .ok(productMapper.toProductDto(productService.save(productMapper.toProduct(productPutDto))));
         */
    }
    //можно рефакторнуть
    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        try {
            return productServiceImpl.deleteById(id) ?
                    ResponseEntity.status(HttpStatus.OK).build() :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (ProductException productException){
            productException.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
