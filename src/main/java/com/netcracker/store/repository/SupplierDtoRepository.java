package com.netcracker.store.repository;

import com.netcracker.store.dto.SupplierDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierDtoRepository extends JpaRepository<SupplierDto,Integer> {
}
