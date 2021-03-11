package com.netcracker.store.service;

import com.netcracker.store.dto.BasketDto;
import com.netcracker.store.dto.HistoryPostDto;
import com.netcracker.store.dto.ProductBasketDto;
import com.netcracker.store.dto.ProductDto;
import com.netcracker.store.entity.History;
import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.User;
import com.netcracker.store.mapper.HistoryMapper;
import com.netcracker.store.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class BasketService {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UserService userService;


    public boolean addHistory(List<BasketDto> basket) {
        if (basket != null) {
            List<ProductBasketDto> history = new ArrayList<>();
            for (BasketDto basketObj : basket
            ) {
                Product product = productService.getById(basketObj.getId());
                int remains = product.getCount() - basketObj.getCount();
                if (remains == 0) {
                    product.setCount(-1);
                } else {
                    product.setCount(product.getCount() - basketObj.getCount());
                }
                history.add(productMapper.toProductBasketDto(product, basketObj.getCount()));
                productService.save(product);
            }
            HistoryPostDto historyPostDto = new HistoryPostDto();
            historyPostDto.setHistory(history);
            historyPostDto.setDate(LocalDate.now());
            historyPostDto.setUser_id(userService.findByLogin(getCurrentUserLogin()).getId());
            historyService.save(historyMapper.toHistory(historyPostDto));
            return true;
        }
        return false;
    }

    private String getCurrentUserLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
