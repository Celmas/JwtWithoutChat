package ru.itis.longpolling.service;

import ru.itis.longpolling.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Optional<Message> save(Message message);
    List<Message> getAll();
}
