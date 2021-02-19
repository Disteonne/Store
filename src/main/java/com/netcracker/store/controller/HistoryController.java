package com.netcracker.store.controller;

import com.netcracker.store.entity.History;
import com.netcracker.store.service.HistoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/histories/{id}")
    public List<History> getAll(@PathVariable(name = "id")Long id){
        return historyService.findByUserId(id);
    }
}
