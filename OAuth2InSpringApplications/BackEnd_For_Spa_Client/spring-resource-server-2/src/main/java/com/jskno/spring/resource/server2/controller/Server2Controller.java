package com.jskno.spring.resource.server2.controller;

import com.jskno.spring.resource.server2.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class Server2Controller {

    @GetMapping
    public List<Account> getAccounts() {
        return List.of(
                Account.builder().name("Patiño").iban("iban1").build(),
                Account.builder().name("ConjuntaGastos").iban("iban2").build(),
                Account.builder().name("IndividualAhorro").iban("iban3").build()
        );
    }
}
