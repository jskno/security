package com.jskno.mykeycloak.eazybank.backend.app.controller;

import com.jskno.mykeycloak.eazybank.backend.app.model.Card;
import com.jskno.mykeycloak.eazybank.backend.app.repository.CardRepository;
import com.jskno.mykeycloak.eazybank.backend.app.service.CardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/my-cards")
    public List<Card> getCardDetails(@RequestParam String email) {
        return cardService.getCardDetails(email);
    }

}
