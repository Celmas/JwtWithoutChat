package ru.itis.longpolling.service;

import ru.itis.longpolling.model.Message;

import java.util.Optional;

public interface MessageService {
    Optional<Message> save(Message message);
}
