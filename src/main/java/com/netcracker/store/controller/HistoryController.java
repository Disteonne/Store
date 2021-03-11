package com.netcracker.store.controller;

import com.netcracker.store.dto.HistoryDto;
import com.netcracker.store.mapper.HistoryMapper;
import com.netcracker.store.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
public class HistoryController {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private HistoryMapper historyMapper;


    @GetMapping("/user/{id}/histories")
    public List<HistoryDto> getAll(@PathVariable(name = "id") Long id) {
        //return historyMapper.toHistoryDtoList(historyService.findByUserId(id));
        return null;
    }

    //можно выводить с поставщиком
    @GetMapping("/history")
    public List<HistoryDto> get(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                @RequestParam(required = false, defaultValue = "0") Integer page,
                                @RequestParam(required = false, defaultValue = "5") Integer size,
                                @RequestParam(required = false, defaultValue = "date") String sortName,
                                @RequestParam(required = false, defaultValue = "desc") String orderBy) {

        return historyMapper.toHistoryDtoList(historyService.findByUserId(date,page,size,Sort.by(Sort.Direction.fromString(orderBy),sortName)));
    }
}
