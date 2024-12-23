package com.jskno.mykeycloak.eazybank.backend.app.service;

import com.jskno.mykeycloak.eazybank.backend.app.exception.DuplicatedCustomerException;
import com.jskno.mykeycloak.eazybank.backend.app.model.Customer;
import com.jskno.mykeycloak.eazybank.backend.app.repository.CustomerRepository;
import com.jskno.mykeycloak.eazybank.backend.app.utils.SingletonCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
            .stream()
            .collect(SingletonCollector.toSingleton(new DuplicatedCustomerException(email)));
    }

}
