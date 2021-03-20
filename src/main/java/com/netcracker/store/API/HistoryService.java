package com.netcracker.store.API;

import com.netcracker.store.entity.History;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface HistoryService {

    List<History> getAll();

    History save(History history);

    List<History> getAll(int page, int size, Sort sort);

    List<History> findByUserId(LocalDate date, int page, int size, Sort sort);
}
