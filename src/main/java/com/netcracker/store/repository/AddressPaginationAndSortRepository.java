package com.netcracker.store.repository;

import com.netcracker.store.entity.Address;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressPaginationAndSortRepository extends PagingAndSortingRepository<Address,Integer> {
}
