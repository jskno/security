package com.jskno.client.app.controller;

import com.jskno.client.app.model.MessageDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @GetMapping
    public List<MessageDto> message() {
        return List.of(
                new MessageDto("Hello World!"),
                new MessageDto("Hello Spring Boot!"));
    }

    @PostMapping
    public MessageDto sendMessage(@RequestBody MessageDto messageDto) {
        return messageDto;
    }
}
