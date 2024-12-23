package com.jskno.myeazybankbackendapp.controller;

import com.jskno.myeazybankbackendapp.model.Loan;
import com.jskno.myeazybankbackendapp.repository.LoanRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanRepository loanRepository;

    @GetMapping("/my-loans")
    public List<Loan> getLoansDetails(@RequestParam Long customerId) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(customerId);
    }

}
