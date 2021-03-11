package com.netcracker.store.service;

import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Integer getCountAll() {
        return productRepository.countAllProduct();
    }

    public Integer getCountByName(String name) {
        return productRepository.countByNameContaining(name);
    }

    public Product getById(Long id) {
        return productRepository.getOne(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean deleteById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public List<Product> getAll(String type, String name, int page, int size, Sort sort) {
        if (name == null && type == null) {
            return productRepository.findAll(PageRequest.of(page, size, sort)).getContent();
        }
        if (type != null && name == null) {
            return productRepository.findAllByType(type, PageRequest.of(page, size, sort));
        }
        return productRepository.findAllByNameContaining(name, PageRequest.of(page, size, sort));
    }

    public Product getByName(String name){
        return productRepository.findByName(name);
    }

    public Product getByAll(String name, String type, BigDecimal price, Integer count, String info, Supplier supplier) {
        return productRepository.findByNameAndCountAndInfoAndPriceAndTypeAndSupplier(name, count, info, price, type, supplier);
    }
}
