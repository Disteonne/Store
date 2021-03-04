package com.netcracker.store.repository;

import com.netcracker.store.entity.History;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query(value = "SELECT h FROM History h WHERE h.date=:date AND h.user.id=:id")
    List<History> findAllByDate(@Param("date") LocalDate date,@Param("id") Long id,Pageable pageable);

}
