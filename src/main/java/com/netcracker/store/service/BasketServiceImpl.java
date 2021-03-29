package com.netcracker.store.service;

import com.netcracker.store.API.BasketService;
import com.netcracker.store.API.UserService;
import com.netcracker.store.dto.BasketDto;
import com.netcracker.store.dto.HistoryDto;
import com.netcracker.store.dto.ProductDto;
import com.netcracker.store.entity.Product;
import com.netcracker.store.exception.ProductException;
import com.netcracker.store.mapper.HistoryMapstructMapper;
import com.netcracker.store.mapper.ProductMapstructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private HistoryServiceImpl historyServiceImpl;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private UserService userServiceImpl;



    public boolean addHistory(List<BasketDto> basket) throws ProductException {
        if (basket != null) {
            List<ProductDto> history = new ArrayList<>();
            for (BasketDto basketObj : basket
            ) {
                Product product = productServiceImpl.getById(basketObj.getId());
                if(product.getCount()==-1){
                    throw new ProductException("Product is missing");
                }
                int remains = product.getCount() - basketObj.getCount();
                if (remains == 0) {
                    product.setCount(-1);
                } else {
                    product.setCount(product.getCount() - basketObj.getCount());
                }
                //history.add(productMapper.toProductBasketDto(product, basketObj.getCount()));
                history.add(ProductMapstructMapper.PRODUCT_MAPSTRUCT_MAPPER.toProductBasketDto(product, basketObj.getCount()));
                productServiceImpl.save(product);
            }
            HistoryDto historyPostDto = new HistoryDto();
            historyPostDto.setHistory(history);
            historyPostDto.setDate(LocalDate.now());
            historyPostDto.setUserId(userServiceImpl.findByLogin(getCurrentUserLogin()).getId());
            //historyService.save(historyMapper.toHistory(historyPostDto));
            historyServiceImpl.save(HistoryMapstructMapper.HISTORY_MAPSTRUCT_MAPPER.mapToHistoryPost(historyPostDto));
            return true;
        }
        return false;
    }

    private String getCurrentUserLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
