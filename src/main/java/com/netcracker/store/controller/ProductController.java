package com.netcracker.store.controller;

import com.netcracker.store.entity.Product;
import com.netcracker.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Product> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable(value = "id") int id){
        return service.getProductById(id);
    }

    @PostMapping("/save")
    public Map<String,Boolean> save(@RequestBody Product product){
        return service.saveProduct(product);
    }

    @DeleteMapping("/delete")
    public Map<String,Boolean> delete(@RequestBody Product product){
        return service.deleteProduct(product);
    }
    @DeleteMapping("/delete/{id}")
    public Map<String,Boolean> delete(@PathVariable(value = "id") int id){
        return service.deleteProductById(id);
    }

}
