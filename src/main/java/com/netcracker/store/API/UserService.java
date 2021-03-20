package com.netcracker.store.API;

import com.netcracker.store.entity.User;
import com.netcracker.store.exception.UserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAll();

    User getById(Long id);

    User save(User user);

    User savePassword(User user);

    User getUser(User user);

    User findByLogin(String login);

    boolean deleteById(Long id) throws UserException;

    User getCurrent();

}
