package com.jskno.mykeycloak.eazybank.backend.app.controller;

import com.jskno.mykeycloak.eazybank.backend.app.model.Loan;
import com.jskno.mykeycloak.eazybank.backend.app.repository.LoanRepository;
import com.jskno.mykeycloak.eazybank.backend.app.service.LoanService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("/my-loans")
    public List<Loan> getLoansDetails(@RequestParam String email) {
        return loanService.getLoansDetails(email);
    }

}
