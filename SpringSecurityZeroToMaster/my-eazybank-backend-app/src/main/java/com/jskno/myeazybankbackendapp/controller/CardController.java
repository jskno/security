package com.jskno.myeazybankbackendapp.controller;

import com.jskno.myeazybankbackendapp.model.Card;
import com.jskno.myeazybankbackendapp.repository.CardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardRepository cardRepository;

    @GetMapping("/my-cards")
    public List<Card> getCardDetails(@RequestParam Long customerId) {
        return cardRepository.findByCustomerId(customerId);
    }

}
