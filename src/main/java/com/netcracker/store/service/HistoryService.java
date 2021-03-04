package com.netcracker.store.service;

import com.netcracker.store.entity.History;
import com.netcracker.store.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final UserService userService;

    public List<History> getAll() {
        return historyRepository.findAll();
    }

    public History findById(Long id) {
        return historyRepository.getOne(id);
    }

    public History save(History history) {
        return historyRepository.save(history);
    }

    public boolean deleteById(Long id) {
        try {
            historyRepository.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;
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
