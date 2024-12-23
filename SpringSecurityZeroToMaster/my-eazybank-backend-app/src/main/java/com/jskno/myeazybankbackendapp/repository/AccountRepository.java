package com.jskno.myeazybankbackendapp.repository;

import com.jskno.myeazybankbackendapp.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
	
	Account findByCustomerId(Long customerId);

}
