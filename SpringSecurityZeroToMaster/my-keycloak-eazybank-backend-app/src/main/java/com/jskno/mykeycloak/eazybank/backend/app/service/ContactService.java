package com.jskno.mykeycloak.eazybank.backend.app.service;

import com.jskno.mykeycloak.eazybank.backend.app.model.Contact;
import com.jskno.mykeycloak.eazybank.backend.app.repository.ContactRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public Contact save(Contact contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);
    }

    public Collection<Contact> saveAll(List<Contact> contacts) {
        contacts.forEach(contact -> {
            contact.setContactId(getServiceReqNumber());
            contact.setCreateDt(new Date(System.currentTimeMillis()));
        });
        Iterable<Contact> savedContacts = contactRepository.saveAll(contacts);
        List<Contact> returningContacts = new ArrayList<>();
        savedContacts.forEach(returningContacts::add);
        return returningContacts;
    }

    private String getServiceReqNumber() {
        Random random = new Random();
        return "SR" + random.nextInt(999999999 - 9999) + 9999;
    }

}
