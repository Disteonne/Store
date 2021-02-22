package com.netcracker.store.repository;

import com.netcracker.store.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("SELECT t FROM History t where t.user.id=:id")
    List<History> findByUserId(@Param("id") Long id);
}