package ru.itis.longpolling.service;

import org.springframework.stereotype.Service;
import ru.itis.longpolling.model.Message;
import ru.itis.longpolling.repository.MessageRepository;

import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Optional<Message> save(Message message) {
        return Optional.of(messageRepository.save(message));
    }
}
