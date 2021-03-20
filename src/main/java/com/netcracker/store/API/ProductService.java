package com.netcracker.store.API;

import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.Supplier;
import com.netcracker.store.exception.ProductException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface ProductService {

    List<Product> getAll();

    Integer getCountAll();

    Integer getCountByName(String name);

    Product getById(Long id);

    Product save(Product product);

    boolean deleteById(Long id) throws ProductException;

    List<Product> getAll(String type, String name, int page, int size, Sort sort);

    Product getByName(String name);

    Product getByAll(String name, String type, BigDecimal price, Integer count, String info, Supplier supplier);
}
