package com.jskno.iocode.auth.server.controller;

import com.jskno.iocode.auth.server.entity.CustomRegisteredClient;
import com.jskno.iocode.auth.server.repository.RegisteredClientRepositoryImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final RegisteredClientRepositoryImpl registeredClientRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomRegisteredClient save(@RequestBody CustomRegisteredClient client) {
        return registeredClientRepository.save(client);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomRegisteredClient> getAll() {
        return registeredClientRepository.findAll();
    }

}
