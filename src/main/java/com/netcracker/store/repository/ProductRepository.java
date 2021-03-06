package com.netcracker.store.repository;

import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.Supplier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByNameContaining(String name, Pageable pageable);

    List<Product> findAllByType(String type,Pageable pageable);

    Product findByName(String name);
    Product findByNameAndCountAndInfoAndPriceAndTypeAndSupplier(String name,Integer count,String info,BigDecimal price, String type,Supplier supplier);

    @Query(value = "SELECT COUNT(*) from Product ",nativeQuery = true)
    Integer countAllProduct();

    Integer countByNameContaining(String name);
}
