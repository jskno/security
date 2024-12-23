package com.jskno.myeazybankbackendapp.controller;

import com.jskno.myeazybankbackendapp.model.Account;
import com.jskno.myeazybankbackendapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;

    @GetMapping("/my-account")
    @PostAuthorize("assetBelongToUser(#customerId)")
    public Account getAccountDetails(@RequestParam Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }
}
