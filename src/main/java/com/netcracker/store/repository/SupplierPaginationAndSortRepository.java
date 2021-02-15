package com.netcracker.store.repository;

import com.netcracker.store.entity.Supplier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierPaginationAndSortRepository extends PagingAndSortingRepository<Supplier,Integer> {
}
