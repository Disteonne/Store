package com.netcracker.store.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HistoryPostDto {


    private LocalDate date;
    private List<ProductBasketDto> history = new ArrayList<>();
    private Long userId;
}
