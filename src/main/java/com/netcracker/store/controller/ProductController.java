package com.netcracker.store.controller;

import com.netcracker.store.dto.*;
import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.User;
import com.netcracker.store.exception.TypeNotFoundException;
import com.netcracker.store.mapper.HistoryMapper;
import com.netcracker.store.mapper.ProductMapper;
import com.netcracker.store.service.BasketService;
import com.netcracker.store.service.HistoryService;
import com.netcracker.store.service.ProductService;
import com.netcracker.store.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final BasketService basketService;

    @GetMapping("/products")
    public List<ProductDto> getAll(@RequestParam(required = false) String type,
                                   @RequestParam(required = false) String nameLike,
                                   @RequestParam(required = false, defaultValue = "0") Integer page,
                                   @RequestParam(required = false, defaultValue = "5") Integer size,
                                   @RequestParam(required = false, defaultValue = "name") String sortName,
                                   @RequestParam(required = false, defaultValue = "asc") String orderBy) {
        return productMapper
                .toProductDtoList(productService.
                        getAll(type, nameLike, page, size, Sort.by(Sort.Direction.fromString(orderBy), sortName)));
    }

    @GetMapping("/all/products")
    public List<ProductDto> getAllProduct() {
        return productMapper.toProductDtoList(productService.getAll());
    }

    @GetMapping("/count")
    public Integer getCount(@RequestParam(required = false, value = "name") String name) {
        if (name == null) {
            return productService.getCountAll();
        } else
            return productService.getCountByName(name);
    }

    @GetMapping("/product/{id}")
    public ProductDto getById(@PathVariable(value = "id") Long id) {
        return productMapper.toProductDto(productService.getById(id));
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> save(@Valid @RequestBody ProductPostDto productPostDto) throws TypeNotFoundException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productMapper.toProductDto(productService.save(productMapper.toProduct(productPostDto))));
    }

    @PostMapping("/basket")
    public ResponseEntity<Void> basket(@RequestBody List<BasketDto> basket) {
        return basketService.addHistory(basket) ? ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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

    @RequestMapping(value = "/saveMap")
    public void saveLayout(@RequestParam(value = "saveMapJs") Map<String, Integer> saveMapJs) {
        System.out.println(saveMapJs.toString());
    }
}
