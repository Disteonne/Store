package com.netcracker.store.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class HistoryDto {

    private Long id;
    private LocalDate date;
    private List<ProductDto> history = new ArrayList<>();
    private Long userId;
}
