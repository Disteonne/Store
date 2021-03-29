package com.netcracker.store.API;

import com.netcracker.store.dto.BasketDto;
import com.netcracker.store.exception.ProductException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BasketService {

    boolean addHistory(List<BasketDto> basket) throws ProductException;

}
