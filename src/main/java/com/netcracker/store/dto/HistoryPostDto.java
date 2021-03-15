package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HistoryPostDto {

    private LocalDate date;
    private List<ProductBasketDto> history = new ArrayList<>();
    private Long userId;
}
