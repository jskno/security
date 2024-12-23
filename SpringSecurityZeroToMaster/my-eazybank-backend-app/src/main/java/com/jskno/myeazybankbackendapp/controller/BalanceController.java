package com.jskno.myeazybankbackendapp.controller;

import com.jskno.myeazybankbackendapp.model.AccountTransaction;
import com.jskno.myeazybankbackendapp.repository.AccountTransactionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTransactionRepository accountTransactionRepository;

    @GetMapping("/my-balance")
    @PostAuthorize("returnObject[0].customerId == authentication.principal.customerId")
//    @PostAuthorize("'happy@example.com' == authentication.principal.name")
//    @PostAuthorize("returnObject[0].accountNumber == returnObject[1].accountNumber")
    public List<AccountTransaction> getBalanceDetails(@RequestParam Long customerId) {
        return accountTransactionRepository.findByCustomerIdOrderByTransactionDtDesc(customerId);
    }
}
