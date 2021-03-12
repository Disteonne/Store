package com.netcracker.store.mapper;

import com.netcracker.store.dto.HistoryDto;
import com.netcracker.store.dto.HistoryPostDto;
import com.netcracker.store.entity.History;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoryMapstructMapper {

    HistoryDto mapToHistoryDto(History history);

    @Mapping(source = "id",target = "")
    History mapToHistory(HistoryPostDto historyPostDto);

    List<HistoryDto> toHistoryDtoList(List<History> historyList);
}
