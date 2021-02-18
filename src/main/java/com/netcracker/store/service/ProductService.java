package com.netcracker.store.service;

import com.netcracker.store.entity.Product;
import com.netcracker.store.mapper.ProductMapper;
import com.netcracker.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/*
        Сервис не должен знать про DTO
 */

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
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
        }catch (Exception ex){
            return false;
        }
        return true;
    }

    public List<Product> getAll(int page, int size, Sort sort) {
        Page<Product> result = productRepository.findAll(PageRequest.of(page,size,sort));
            return result.getContent();
    }
}
