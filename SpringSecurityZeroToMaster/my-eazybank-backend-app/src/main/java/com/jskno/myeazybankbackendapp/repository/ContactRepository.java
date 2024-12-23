package com.jskno.myeazybankbackendapp.repository;

import com.jskno.myeazybankbackendapp.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
}
