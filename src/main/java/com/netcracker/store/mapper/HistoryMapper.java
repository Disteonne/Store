package com.netcracker.store.mapper;

import com.netcracker.store.dto.HistoryDto;
import com.netcracker.store.entity.History;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HistoryMapper {

    private final ProductMapper productMapper;

    public HistoryDto toHistoryDto(History history) {
        if (history == null) {
            return null;
        }
        HistoryDto dto = new HistoryDto();
        dto.setId(history.getId());
        dto.setDate(history.getDate());
        dto.setHistory(productMapper.toProductDtoList(history.getHistory()));
        return dto;
    }

    public List<HistoryDto> toHistoryDtoList(List<History> historyList) {
        List<HistoryDto> result = new ArrayList<>();
        if (historyList == null) {
            return result;
        }
        historyList.forEach(product -> result.add(toHistoryDto(product)));
        return result;
    }
}
