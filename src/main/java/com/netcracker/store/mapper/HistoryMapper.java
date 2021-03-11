package com.netcracker.store.mapper;

import com.netcracker.store.dto.HistoryDto;
import com.netcracker.store.dto.HistoryPostDto;
import com.netcracker.store.entity.History;
import com.netcracker.store.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HistoryMapper {

    @Autowired
    private ProductMapper productMapper;

    public HistoryDto toHistoryDto(History history) {
        if (history == null) {
            return null;
        }
        HistoryDto dto = new HistoryDto();
        dto.setId(history.getId());
        dto.setDate(history.getDate());
        dto.setHistory(history.getHistory());
        return dto;
    }

    public History toHistory(HistoryPostDto historyPostDto){
        History history=new History();
        history.setDate(historyPostDto.getDate());
        history.setHistory(historyPostDto.getHistory());
        User user=new User();
        user.setId(historyPostDto.getUser_id());
        history.setUser(user);
        return history;
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
