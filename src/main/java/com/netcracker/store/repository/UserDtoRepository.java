package com.netcracker.store.repository;

import com.netcracker.store.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDtoRepository extends JpaRepository<UserDto,Integer> {
}
