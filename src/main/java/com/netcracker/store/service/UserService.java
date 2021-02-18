package com.netcracker.store.service;

import com.netcracker.store.entity.User;
import com.netcracker.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id){
        return userRepository.getOne(id);
    }

    public User save(User user) {
       return userRepository.save(user);
    }

    public boolean deleteById(Long id){
       try {
           userRepository.deleteById(id);
       }catch (Exception ex){
           return false;
       }
       return true;
    }

    public List<User> getAll(int page, int size, Sort sort){
        Page<User> result=userRepository.findAll(PageRequest.of(page,size,sort));
        return result.getContent();
    }
}
