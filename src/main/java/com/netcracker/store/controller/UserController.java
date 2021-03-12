package com.netcracker.store.controller;

import com.netcracker.store.dto.*;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.User;
import com.netcracker.store.mapper.UserMapper;
import com.netcracker.store.mapper.UserProfileMapper;
import com.netcracker.store.mapper.UserProfileMapstructMapper;
import com.netcracker.store.service.AddressService;
import com.netcracker.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserProfileMapper userProfileMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/registration")
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserPostDto userPostDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toUserDto(userService.save(userMapper.toUser(userPostDto))));
    }

    @GetMapping("/profile")
    public ProfileDto getInfo() {
        User user = userService.findByLogin(getCurrentUserLogin());
        Address address = addressService.getById(userMapper.toUserDto(user).getAddressId());
        return userProfileMapper.toInfoDto(user, address);
    }

    @PatchMapping("/profile/{id}")
    public UserProfilePatchDto saveInfo(@PathVariable(value = "id") Long id, @RequestBody UserProfilePatchDto userProfilePatchDto) {
        User user= UserProfileMapstructMapper.INFO_MAPSTRUCT_MAPPER.mapToUser(userProfilePatchDto,getCurrentUserLogin(),userService);
        Address newAddress = addressService.save(userProfileMapper.toAddress(userProfilePatchDto, getCurrentUserLogin()));
        user.setAddress(newAddress);
        userService.save(user);
        return userProfilePatchDto;
    }

    @PatchMapping("/profile/password")
    public String changePassword(@RequestBody PasswordDto password) {
        User user = userService.findByLogin(getCurrentUserLogin());
        if (!bCryptPasswordEncoder.matches(password.getPassword(), user.getPassword())) {
            user.setPassword(password.getPassword());
            userService.savePassword(user);
            userService.save(user);
        }
        return password.getPassword();
    }

    @DeleteMapping("/profile/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id,@RequestBody String empty) {
        return userService.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private String getCurrentUserLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
