package ru.itis.longpolling.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.longpolling.exception.CustomException;
import ru.itis.longpolling.model.User;
import ru.itis.longpolling.repository.UserRepository;
import ru.itis.longpolling.security.JwtTokenHelper;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenHelper jwtTokenHelper;

    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenHelper jwtTokenHelper, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenHelper = jwtTokenHelper;
        this.authenticationManager = authenticationManager;
    }

    public String signin(String login, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
            Optional<User> user = userRepository.findByLogin(login);
            if (user.isEmpty()) {
                throw new CustomException("User is not found", HttpStatus.BAD_REQUEST);
            }
            return jwtTokenHelper.createToken(login, user.get().getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User user) {
        if (!userRepository.existsByLogin(user.getLogin())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return jwtTokenHelper.createToken(user.getLogin(), user.getRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String login) {
        userRepository.deleteByLogin(login);
    }

    public String refresh(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isEmpty()) {
            throw new CustomException("User is not found", HttpStatus.BAD_REQUEST);
        }
        return jwtTokenHelper.createToken(login, user.get().getRoles());
    }

    @Override
    public User getUserByLogin(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isEmpty()) {
            throw new CustomException("User is not found", HttpStatus.BAD_REQUEST);
        }
        return user.get();
    }


}
