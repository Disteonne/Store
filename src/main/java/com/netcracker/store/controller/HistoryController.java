package com.netcracker.store.controller;

import com.netcracker.store.dto.HistoryDto;
import com.netcracker.store.mapper.HistoryMapper;
import com.netcracker.store.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;
    private final HistoryMapper historyMapper;

    @GetMapping("/user/{id}/histories")
    public List<HistoryDto> getAll(@PathVariable(name = "id") Long id) {
        return historyMapper.toHistoryDtoList(historyService.findByUserId(id));
    }
}
