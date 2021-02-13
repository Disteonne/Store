package com.netcracker.store.repository;

import com.netcracker.store.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDtoRepository extends JpaRepository<ProductDto,Integer> {
}
