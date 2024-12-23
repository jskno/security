package com.jskno.myeazybankbackendapp.repository;

import com.jskno.myeazybankbackendapp.model.AccountTransaction;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionRepository extends CrudRepository<AccountTransaction, Long> {
	
	List<AccountTransaction> findByCustomerIdOrderByTransactionDtDesc(Long customerId);

}
