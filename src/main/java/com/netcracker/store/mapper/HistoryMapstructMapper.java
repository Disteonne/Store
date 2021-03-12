package com.netcracker.store.mapper;

import com.netcracker.store.dto.HistoryDto;
import com.netcracker.store.dto.HistoryPostDto;
import com.netcracker.store.entity.History;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoryMapstructMapper {

    HistoryMapstructMapper HISTORY_MAPSTRUCT_MAPPER= Mappers.getMapper(HistoryMapstructMapper.class);

    HistoryDto mapToHistoryDto(History history);
    //source-откуда target-куда
    @Mapping(source = "userId",target = "user.id")
    History mapToHistory(HistoryPostDto historyPostDto);

    List<HistoryDto> toHistoryDtoList(List<History> historyList);
}
