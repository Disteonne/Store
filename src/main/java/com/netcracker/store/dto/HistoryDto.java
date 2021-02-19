package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HistoryDto {

    private Long id;
    private LocalDateTime date;
    private List<ProductDto> history = new ArrayList<>();
}
