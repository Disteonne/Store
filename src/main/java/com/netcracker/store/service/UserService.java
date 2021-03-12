package com.netcracker.store.service;

import com.netcracker.store.entity.User;
import com.netcracker.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    public User save(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        //при любом сохранении энкодит пароль,поэтому логика вынесена в отдельном методе savePassword
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLogin(user.getLogin());
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public User savePassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public boolean deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public User getCurrent() {
        return findByLogin(getCurrentUserLogin());
    }
    private String getCurrentUserLogin() {//login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
