package com.netcracker.store.service;

import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll(){
        return repository.findAll();
    }

    public Product getProductById(int id){
        return repository.getOne(id);
    }

    public Map<String,Boolean> saveProduct(Product product){
        repository.save(product);
        Map<String,Boolean> result=new HashMap<>();
        result.put("saved",true);
        return result;
    }

    public Map<String,Boolean> deleteProduct(Product product){
        repository.delete(product);
        Map<String,Boolean> result=new HashMap<>();
        result.put("deleted",true);
        return result;
    }

    public Map<String,Boolean> deleteProductById(int id){
        repository.deleteById(id);
        Map<String,Boolean> result=new HashMap<>();
        result.put("deleted",true);
        return result;
    }
}
