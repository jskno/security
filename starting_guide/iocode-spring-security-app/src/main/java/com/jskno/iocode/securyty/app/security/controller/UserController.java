package com.jskno.iocode.securyty.app.security.controller;

import com.jskno.iocode.securyty.app.security.dto.AuthUser;
import com.jskno.iocode.securyty.app.security.dto.RegUser;
import com.jskno.iocode.securyty.app.security.entity.User;
import com.jskno.iocode.securyty.app.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody RegUser regUser) {
        return userService.register(regUser);
    }

    @PostMapping("/auth")
    public String auth(@RequestBody AuthUser authUser) {
        return userService.auth(authUser);
    }

}
