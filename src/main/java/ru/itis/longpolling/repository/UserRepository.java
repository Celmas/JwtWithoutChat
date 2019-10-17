package ru.itis.longpolling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.longpolling.model.User;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
