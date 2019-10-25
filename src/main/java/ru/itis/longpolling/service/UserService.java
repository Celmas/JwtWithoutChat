package ru.itis.longpolling.service;

import ru.itis.longpolling.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface UserService {
    String signin(String login, String password);
    String signup(User user);
    void delete(String login);
    String refresh(String login);
    User getUserByLogin(String login);
}
