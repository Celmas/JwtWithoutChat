package ru.itis.longpolling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.longpolling.model.Message;


public interface MessageRepository extends JpaRepository<Message, Long> {
}
