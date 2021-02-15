package com.netcracker.store.repository;

import com.netcracker.store.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaginationAndSortRepository extends PagingAndSortingRepository<User,Integer> {

}
