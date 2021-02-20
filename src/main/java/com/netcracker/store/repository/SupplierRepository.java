package com.netcracker.store.repository;

import com.netcracker.store.entity.Supplier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {

    List<Supplier> findSupplierByNameStartingWith(String nameLike, Pageable pageable);
}
