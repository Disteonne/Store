package com.netcracker.store.mapper;

import com.netcracker.store.dto.HistoryDto;
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
    @Mapping(source = "historyPostDto.date",target = "date")
    @Mapping(source = "historyPostDto.history",target ="history" )
    @Mapping(source = "historyPostDto.userId",target = "user.id")
    History mapToHistoryPost(HistoryDto historyPostDto);

    List<HistoryDto> toHistoryDtoList(List<History> historyList);
}
