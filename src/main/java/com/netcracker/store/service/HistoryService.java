package com.netcracker.store.service;

import com.netcracker.store.entity.History;
import com.netcracker.store.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private UserService userService;

    public List<History> getAll() {
        return historyRepository.findAll();
    }

    public History save(History history) {
        return historyRepository.save(history);
    }

    public List<History> getAll(int page, int size, Sort sort) {
        Page<History> result = historyRepository.findAll(PageRequest.of(page, size, sort));
        return result.getContent();
    }

    public List<History> findByUserId(LocalDate date,int page,int size,Sort sort) {
        if(date==null){
          return getAll(page,size,sort);
        }
        return historyRepository.findAllByDate(date,userService.getCurrent().getId(),PageRequest.of(page,size,sort));
    }
}
