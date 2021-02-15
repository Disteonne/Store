package com.netcracker.store.repository;

import com.netcracker.store.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPaginationAndSortRepository extends PagingAndSortingRepository<Product,Integer> {
}
