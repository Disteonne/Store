package com.netcracker.store.controller;

import com.netcracker.store.API.AddressService;
import com.netcracker.store.API.UserService;
import com.netcracker.store.dto.*;
import com.netcracker.store.entity.Address;
import com.netcracker.store.entity.User;
import com.netcracker.store.entity.UsersRole;
import com.netcracker.store.exception.UserException;
import com.netcracker.store.mapper.*;
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
    private UserService userServiceImpl;
    @Autowired
    private AddressService addressServiceImpl;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
    getUser()-проверяет наличие такого-же пользователя по логину.т.к.логин-уникален
     */
    @PostMapping("/registration")
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userPostDto) {
        //userPostDto.getRole().add(UsersRole.ROLE_USER);
        User user = UserMapstructMapper.USER_MAPSTRUCT_MAPPER.toUser(userPostDto);
        user.getUsersRoles().add(UsersRole.ROLE_USER);
        user.setAddress(addressServiceImpl.save(addressServiceImpl.getAddress(user.getAddress())));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                UserMapstructMapper.USER_MAPSTRUCT_MAPPER.
                        mapToUserDto(userServiceImpl.save(userServiceImpl.getUser(
                                userServiceImpl.savePassword(user)))));
    }

    @GetMapping("/profile")
    public ProfileDto getInfo() {
        User user = userServiceImpl.findByLogin(getCurrentUserLogin());
        Address address = addressServiceImpl.getById(UserMapstructMapper.USER_MAPSTRUCT_MAPPER.mapToUserDto(user).getAddressId());
        // return userProfileMapper.toInfoDto(user, address);
        return UserProfileMapstructMapper.USER_PROFILE_MAPSTRUCT_MAPPER.toProfileDto(user, address);
    }
    //рефакторни
    //не нужны в паммере ифы т.к данные полностью поднимаются PUT
    @PutMapping("/profile")
    public UserDto saveInfo(@Valid @RequestBody UserDto userProfilePatchDto) {
        String password = userServiceImpl.findByLogin(userProfilePatchDto.getLogin()).getPassword();
        User user = UserProfileMapstructMapper.USER_PROFILE_MAPSTRUCT_MAPPER.mapToUser(userProfilePatchDto, getCurrentUserLogin(), userServiceImpl);
        user.setPassword(password);
        Address newAddress = AddressMapstructMapper.ADDRESS_MAPSTRUCT_MAPPER.mapToAddress(userProfilePatchDto);
        user.setAddress(addressServiceImpl.save(addressServiceImpl.getAddress(newAddress)));
        userServiceImpl.save(user);

        return userProfilePatchDto;
    }

    @PatchMapping("/profile/password")
    public String changePassword(@RequestBody PasswordDto password) {
        User user = userServiceImpl.findByLogin(getCurrentUserLogin());
        if (!bCryptPasswordEncoder.matches(password.getPassword(), user.getPassword())) {
            user.setPassword(password.getPassword());
            userServiceImpl.savePassword(user);
            userServiceImpl.save(user);
        }
        return password.getPassword();
    }

    //можно рефакторнуть
    @DeleteMapping("/profile/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id, @RequestBody String empty) {
        try {
            return userServiceImpl.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (UserException userException) {
            userException.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private String getCurrentUserLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
