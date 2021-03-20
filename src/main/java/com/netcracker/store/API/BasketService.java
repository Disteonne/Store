package com.netcracker.store.API;

import com.netcracker.store.dto.BasketDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BasketService {

    boolean addHistory(List<BasketDto> basket);

}
