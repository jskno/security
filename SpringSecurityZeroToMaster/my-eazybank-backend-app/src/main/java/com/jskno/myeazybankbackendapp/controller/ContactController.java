package com.jskno.myeazybankbackendapp.controller;

import com.jskno.myeazybankbackendapp.model.Contact;
import com.jskno.myeazybankbackendapp.repository.ContactRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactRepository contactRepository;

    @PostMapping("/contact")
    @PreAuthorize("hasPermission(#contact, 'write')")
    public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);
    }

    @PostMapping("/contacts")
    @PreFilter("filterObject.contactName != 'Test'")
    @PostFilter("filterObject.contactName != 'Test2'")
    public Collection<Contact> saveContactInquiryDetails(@RequestBody List<Contact> contacts) {
        contacts.stream().forEach(contact -> {
            contact.setContactId(getServiceReqNumber());
            contact.setCreateDt(new Date(System.currentTimeMillis()));
        });
        Iterable<Contact> savedContacts = contactRepository.saveAll(contacts);
        List<Contact> returningContacts = new ArrayList<>();
        savedContacts.forEach(contact -> returningContacts.add(contact));
        return returningContacts;
    }

    private String getServiceReqNumber() {
        Random random = new Random();
        return "SR" + random.nextInt(999999999 - 9999) + 9999;
    }

}
