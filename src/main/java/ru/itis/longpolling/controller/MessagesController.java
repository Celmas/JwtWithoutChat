package ru.itis.longpolling.controller;

import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.longpolling.dto.MessageDto;
import ru.itis.longpolling.model.Message;
import ru.itis.longpolling.security.JwtTokenHelper;
import ru.itis.longpolling.service.MessageService;
import ru.itis.longpolling.service.UserService;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class MessagesController {
    private final Map<String, List<Message>> messages = new HashMap<>();

    private MessageService messageService;
    private UserService userService;

    public MessagesController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/messages")
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto messageDto, Authentication authentication) {
        String token = authentication.getName();
        System.out.println(token);
        if (!messages.containsKey(token)) {
            messages.put(token, new ArrayList<>());
        }
        Optional<Message> message = messageService.save(Message.builder()
                .sender(userService.getUserByLogin(token))
                .time(LocalDateTime.now())
                .value(messageDto.getText())
                .build());
        for (List<Message> pageMessages : messages.values()) {
            synchronized (pageMessages) {
                pageMessages.add(message.get());
                pageMessages.notifyAll();
            }
        }
        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessagesForPage(Authentication authentication) {
        String token = authentication.getName();
        synchronized (messages.get(token)) {
            if (messages.get(token).isEmpty()) {
                messages.get(token).wait();
            }
            List<Message> response = new ArrayList<>(messages.get(token));
            messages.get(token).clear();
            return ResponseEntity.ok(response);
        }
    }

    @SneakyThrows
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/allmessages")
    public ResponseEntity<List<Message>> getAllMessagesForPage() {
        return ResponseEntity.ok(messageService.getAll());
    }
}
