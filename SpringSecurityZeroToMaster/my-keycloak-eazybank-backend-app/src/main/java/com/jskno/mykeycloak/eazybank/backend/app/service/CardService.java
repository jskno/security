package com.jskno.mykeycloak.eazybank.backend.app.service;

import com.jskno.mykeycloak.eazybank.backend.app.model.Card;
import com.jskno.mykeycloak.eazybank.backend.app.model.Customer;
import com.jskno.mykeycloak.eazybank.backend.app.repository.CardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CustomerService customerService;
    private final CardRepository cardRepository;

    public List<Card> getCardDetails(String customerEmail) {
        Customer customer = customerService.getCustomerByEmail(customerEmail);
        return cardRepository.findByCustomerId(customer.getId());
    }

}
