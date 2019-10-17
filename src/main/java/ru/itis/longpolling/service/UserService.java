package ru.itis.longpolling.service;

import ru.itis.longpolling.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface UserService {
    String signin(String username, String password);
    String signup(User user);
    void delete(String username);
    String refresh(String username);
    User getUserByUsername(String username);
}
