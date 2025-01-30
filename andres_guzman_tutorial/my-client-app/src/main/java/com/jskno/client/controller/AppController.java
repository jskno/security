package com.jskno.client.controller;

import com.jskno.client.models.Message;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class AppController {

    @GetMapping
    public List<Message> getMessages() {
        return List.of(
            new Message("First message"),
            new Message("Second message")
        );
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        System.out.println("Message stored: " + message);
        return message;
    }

}
