package com.netcracker.store.controller;

import com.netcracker.store.dto.UserDto;
import com.netcracker.store.dto.UserPostDto;
import com.netcracker.store.dto.UserPutDto;
import com.netcracker.store.entity.User;
import com.netcracker.store.mapper.UserMapper;
import com.netcracker.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/users")
    public List<UserDto> getAll(@RequestParam(required = false) String street,
                                @RequestParam(required = false, defaultValue = "0") Integer page,
                                @RequestParam(required = false, defaultValue = "10") Integer size,
                                @RequestParam(required = false, defaultValue = "name") String sortName,
                                @RequestParam(required = false, defaultValue = "asc") String orderBy) {

        return userMapper
                .toUserDtoList(userService
                        .getAll(street, page, size, Sort.by(Sort.Direction.fromString(orderBy), sortName)));
    }

    @GetMapping("/user/{id}")
    public UserDto getById(@PathVariable(value = "id") Long id) {
        return userMapper.toUserDto(userService.getById(id));
    }

    @GetMapping("/user/current")
    public UserDto currentUser(){
        return userMapper.toUserDto(userService.getCurrent());
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserPostDto userPostDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toUserDto(userService.save(userMapper.toUser(userPostDto))));
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id) {
        return userService.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/user")
    public ResponseEntity<UserDto> put(@Valid @RequestBody UserPutDto userPutDto) {
        return ResponseEntity.ok(userMapper.toUserDto(userService.save(userMapper.toUser(userPutDto))));
    }

    @PatchMapping("/user/current")
    public ResponseEntity<UserDto> patch(@RequestBody Map<String,String> update){
        return ResponseEntity.ok(userMapper.toUserDto(userService.save(userService.getUpdated(update))));
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<UserDto> patch(@PathVariable(name = "id") Long id, @RequestBody UserDto userDto) {
        User user = userService.getById(id);
        return ResponseEntity.ok(userMapper.toUserDto(userService.save(userMapper.patch(user, userDto))));
    }

}
