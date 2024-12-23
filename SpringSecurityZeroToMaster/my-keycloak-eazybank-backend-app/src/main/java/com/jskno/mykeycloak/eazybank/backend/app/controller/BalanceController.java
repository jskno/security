package com.jskno.mykeycloak.eazybank.backend.app.controller;

import com.jskno.mykeycloak.eazybank.backend.app.model.AccountTransaction;
import com.jskno.mykeycloak.eazybank.backend.app.repository.AccountTransactionRepository;
import com.jskno.mykeycloak.eazybank.backend.app.service.BalanceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BalanceController {
    private final BalanceService balanceService;

    @GetMapping("/my-balance")
    public List<AccountTransaction> getBalanceDetails(@RequestParam String email) {
        return balanceService.findByCustomerEmail(email);
    }
}
