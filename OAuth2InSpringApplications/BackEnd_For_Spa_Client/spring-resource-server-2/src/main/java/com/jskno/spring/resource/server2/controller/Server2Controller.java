package com.jskno.spring.resource.server2.controller;

import com.jskno.spring.resource.server2.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class Server2Controller {

    @GetMapping
    public List<Account> getAccounts() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username = authentication.getName();
        Object principal = authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        log.info("username: {}, principal: {}, authorities: {}", username, principal, authorities);
        return List.of(
                Account.builder().name("Patiño").iban("iban1").build(),
                Account.builder().name("ConjuntaGastos").iban("iban2").build(),
                Account.builder().name("IndividualAhorro").iban("iban3").build()
        );
    }
}
