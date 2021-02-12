package com.netcracker.store.controller;

import com.netcracker.store.entity.Supplier;
import com.netcracker.store.entity.User;
import com.netcracker.store.exeption.NotFoundException;
import com.netcracker.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<User> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable(value= "id") int id) throws NotFoundException {
        return service.getUserById(id);
    }

    @PostMapping("/save")
    public Map<String,Boolean> save(@RequestBody User user){
        return service.saveUser(user);
    }

    @DeleteMapping("/delete")
    public Map<String,Boolean> delete(@RequestBody User user){
        return service.deleteUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Boolean> deleteById(@PathVariable(value = "id") int id) throws NotFoundException {
        return service.deleteUserById(id);
    }
}
