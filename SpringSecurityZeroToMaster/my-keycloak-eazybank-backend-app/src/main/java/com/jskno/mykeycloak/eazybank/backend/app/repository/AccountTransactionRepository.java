package com.jskno.mykeycloak.eazybank.backend.app.repository;

import com.jskno.mykeycloak.eazybank.backend.app.model.AccountTransaction;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionRepository extends CrudRepository<AccountTransaction, Long> {
	List<AccountTransaction> findByCustomerIdOrderByTransactionDtDesc(Long customerId);

}
