package com.jskno.mykeycloak.eazybank.backend.app.service;

import com.jskno.mykeycloak.eazybank.backend.app.model.Account;
import com.jskno.mykeycloak.eazybank.backend.app.model.AccountTransaction;
import com.jskno.mykeycloak.eazybank.backend.app.model.Customer;
import com.jskno.mykeycloak.eazybank.backend.app.repository.AccountRepository;
import com.jskno.mykeycloak.eazybank.backend.app.repository.AccountTransactionRepository;
import com.jskno.mykeycloak.eazybank.backend.app.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final AccountTransactionRepository accountTransactionRepository;
    private final CustomerService customerService;
    public List<AccountTransaction> findByCustomerEmail(String customerEmail) {
        Customer customer = customerService.getCustomerByEmail(customerEmail);
        return accountTransactionRepository.findByCustomerIdOrderByTransactionDtDesc(customer.getId());
    }

}
