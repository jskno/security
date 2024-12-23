package com.jskno.mykeycloak.eazybank.backend.app.controller;

import com.jskno.mykeycloak.eazybank.backend.app.model.Account;
import com.jskno.mykeycloak.eazybank.backend.app.repository.AccountRepository;
import com.jskno.mykeycloak.eazybank.backend.app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/my-account")
    public Account getAccountDetails(@RequestParam String email) {
        return accountService.findByCustomerEmail(email);
    }
}
