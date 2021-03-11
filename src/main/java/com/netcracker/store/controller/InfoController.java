package com.netcracker.store.controller;

import com.netcracker.store.dto.InfoDto;
import com.netcracker.store.dto.InfoPostDto;
import com.netcracker.store.dto.PasswordDto;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.User;
import com.netcracker.store.mapper.InfoMapper;
import com.netcracker.store.mapper.UserMapper;
import com.netcracker.store.service.AddressService;
import com.netcracker.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InfoMapper infoMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/info")
    public InfoDto getInfo() {
        User user = userService.findByLogin(getCurrentUserLogin());
        Address address = addressService.getById(userMapper.toUserDto(user).getAddressId());
        return infoMapper.toInfoDto(user, address);
    }

    @PostMapping("/info")
    public InfoPostDto saveInfo(@RequestBody InfoPostDto infoPostDto) {
        User user = infoMapper.toUser(infoPostDto, getCurrentUserLogin());
        Address newAddress = addressService.save(infoMapper.toAddress(infoPostDto, getCurrentUserLogin()));
        user.setAddress(newAddress);
        //userService.save(infoMapper.toUser(infoPostDto, getCurrentUserLogin()));
        userService.save(user);
        return infoPostDto;
    }

    @PostMapping("/password")
    public String changePassword(@RequestBody PasswordDto password) {
        User user = userService.findByLogin(getCurrentUserLogin());
        if (!bCryptPasswordEncoder.matches(password.getPassword(), user.getPassword())) {
            user.setPassword(password.getPassword());
            userService.savePassword(user);
            userService.save(user);
        }
        return password.getPassword();
    }

    private String getCurrentUserLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
