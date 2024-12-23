package com.jskno.mykeycloak.eazybank.backend.app.repository;

import com.jskno.mykeycloak.eazybank.backend.app.model.Loan;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
	List<Loan> findByCustomerIdOrderByStartDtDesc(Long customerId);

}
