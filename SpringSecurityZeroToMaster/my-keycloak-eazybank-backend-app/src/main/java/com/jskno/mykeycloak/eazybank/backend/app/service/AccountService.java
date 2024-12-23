package com.jskno.mykeycloak.eazybank.backend.app.service;

import com.jskno.mykeycloak.eazybank.backend.app.model.Account;
import com.jskno.mykeycloak.eazybank.backend.app.model.Customer;
import com.jskno.mykeycloak.eazybank.backend.app.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    public Account findByCustomerEmail(String customerEmail) {
        Customer customer = customerService.getCustomerByEmail(customerEmail);
        return accountRepository.findByCustomerId(customer.getId())
                .orElse(Account.builder().build());
    }

}
