package com.jskno.client.app.controller;

import com.jskno.client.app.model.MessageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorized")
public class AuthorizationController {

    @GetMapping
    public MessageDto getAuthorization(@RequestParam String code) {
        return new MessageDto(code);
    }
}
