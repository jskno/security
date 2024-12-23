package com.jskno.mykeycloak.eazybank.backend.app.repository;

import com.jskno.mykeycloak.eazybank.backend.app.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
}
