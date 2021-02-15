package com.netcracker.store.repository;

import com.netcracker.store.entity.History;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryPaginationAndSortRepository extends PagingAndSortingRepository<History,Integer> {
}
