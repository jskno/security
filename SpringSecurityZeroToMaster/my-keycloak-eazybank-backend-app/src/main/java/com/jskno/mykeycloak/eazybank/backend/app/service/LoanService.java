package com.jskno.mykeycloak.eazybank.backend.app.service;

import com.jskno.mykeycloak.eazybank.backend.app.model.Loan;
import com.jskno.mykeycloak.eazybank.backend.app.repository.LoanRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final CustomerService customerService;
    private final LoanRepository loanRepository;

    public List<Loan> getLoansDetails(String customerEmail) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(
            customerService.getCustomerByEmail(customerEmail).getId());
    }

}
