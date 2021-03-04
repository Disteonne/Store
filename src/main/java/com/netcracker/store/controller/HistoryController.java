package com.netcracker.store.controller;

import com.netcracker.store.dto.HistoryDto;
import com.netcracker.store.mapper.HistoryMapper;
import com.netcracker.store.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;
    private final HistoryMapper historyMapper;


    @GetMapping("/user/{id}/histories")
    public List<HistoryDto> getAll(@PathVariable(name = "id") Long id) {
        //return historyMapper.toHistoryDtoList(historyService.findByUserId(id));
        return null;
    }

    @GetMapping("/history")
    public List<HistoryDto> get(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                @RequestParam(required = false, defaultValue = "0") Integer page,
                                @RequestParam(required = false, defaultValue = "5") Integer size,
                                @RequestParam(required = false, defaultValue = "date") String sortName,
                                @RequestParam(required = false, defaultValue = "desc") String orderBy) {

        return historyMapper.toHistoryDtoList(historyService.findByUserId(date,page,size,Sort.by(Sort.Direction.fromString(orderBy),sortName)));
    }
}
