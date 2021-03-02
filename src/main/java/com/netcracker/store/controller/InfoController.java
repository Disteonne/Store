package com.netcracker.store.controller;

import com.netcracker.store.dto.InfoDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.User;
import com.netcracker.store.mapper.InfoMapper;
import com.netcracker.store.mapper.UserMapper;
import com.netcracker.store.service.AddressService;
import com.netcracker.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InfoController {

    private final UserService userService;
    private final AddressService addressService;
    private final UserMapper userMapper;
    private final InfoMapper infoMapper;

    @GetMapping("/info")
    public InfoDto getInfo() {
        User user = userService.findByLogin(getCurrentUsername());
        Address address = addressService.getById(userMapper.toUserDto(user).getAddressId());
        return infoMapper.getInfo(user, address, getCurrentUsername());
    }

    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
