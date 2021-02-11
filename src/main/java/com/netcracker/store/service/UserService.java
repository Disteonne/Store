package com.netcracker.store.service;

import com.netcracker.store.entity.Product;
import com.netcracker.store.entity.User;
import com.netcracker.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public List<User> getAll(){
        return repository.findAll();
    }

    public User getUserById(int id){
        return repository.getOne(id);
    }

    public Map<String,Boolean> saveUser(User user){
        repository.save(user);
        Map<String,Boolean> result=new HashMap<>();
        result.put("saved",true);
        return result;
    }

    public Map<String,Boolean> deleteUser(User user){
        repository.delete(user);
        Map<String,Boolean> result=new HashMap<>();
        result.put("deleted",true);
        return result;
    }

    public Map<String,Boolean> deleteUserById(int id){
        repository.deleteById(id);
        Map<String,Boolean> result=new HashMap<>();
        result.put("deleted",true);
        return result;
    }
}
