package ru.itis.longpolling.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.longpolling.model.User;
import ru.itis.longpolling.repository.UserRepository;

import java.util.Optional;

@Service("userDetailsServiceImpl")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final JwtTokenHelper tokenHelper;

    public CustomUserDetailsService(UserRepository userRepository, JwtTokenHelper tokenHelper) {
        this.userRepository = userRepository;
        this.tokenHelper = tokenHelper;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByLogin(login);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User '" + login + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(login)
                .password(user.get().getPassword())
                .authorities(user.get().getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}

