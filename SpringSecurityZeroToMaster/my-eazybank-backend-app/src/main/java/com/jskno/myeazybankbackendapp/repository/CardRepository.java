package com.jskno.myeazybankbackendapp.repository;

import com.jskno.myeazybankbackendapp.model.Card;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

//	@PreAuthorize("hasPermission(#id, 'Foo', 'read')")
//	@PreAuthorize("hasPermission('Card', 'view')")
	@PostAuthorize("hasPermission(returnObject[0], 'view')")
	List<Card> findByCustomerId(Long customerId);

}
