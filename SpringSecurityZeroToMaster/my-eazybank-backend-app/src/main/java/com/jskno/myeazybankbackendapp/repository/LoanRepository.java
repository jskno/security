package com.jskno.myeazybankbackendapp.repository;

import com.jskno.myeazybankbackendapp.model.Loan;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

	@PreAuthorize("hasAuthority('VIEWLOANS')")
	List<Loan> findByCustomerIdOrderByStartDtDesc(Long customerId);

}
