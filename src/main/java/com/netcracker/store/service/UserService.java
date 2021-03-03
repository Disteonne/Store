package com.netcracker.store.service;

import com.netcracker.store.entity.User;
import com.netcracker.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

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

    public List<User> getAll(String street, int page, int size, Sort sort) {
        if (street == null) {
            Page<User> result = userRepository.findAll(PageRequest.of(page, size, sort));
            return result.getContent();
        }
        return userRepository.getUser(street, PageRequest.of(page, size, sort));
    }

    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    public User getCurrent() {
        return findByLogin(getCurrentUsername());
    }

    public User getUpdated(Map<String, String> update) {
        User user = getCurrent();
        for (Map.Entry<String, String> entry : update.entrySet()
        ) {
            if (entry.getKey().equals("name")) {
                user.setName(entry.getValue());
                //return true;
            }
            if (entry.getKey().equals("surname")) {
                user.setSurname(entry.getValue());
                //return true;
            }
            if (entry.getKey().equals("age")) {
                user.setAge(Integer.parseInt(entry.getValue()));
                //return true;
            }
            if (entry.getKey().equals("login")) {
                user.setLogin(entry.getValue());
                //return true;
            }
            if (entry.getKey().equals("password")) {
                if (passwordEncoder.matches(entry.getValue(), user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(entry.getValue()));
                    //return true;
                }
            }
        }
        return user;
    }

    private String getCurrentUsername() {//login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    /*
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        Set<GrantedAuthority> grantedAuthorities=new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Credentials.USER.toString()));
        grantedAuthorities.add(new SimpleGrantedAuthority(Credentials.ADMIN.toString()));
        return new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(),grantedAuthorities);
    }
     */
}
