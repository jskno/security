package com.jskno.mykeycloak.eazybank.backend.app.controller;

import com.jskno.mykeycloak.eazybank.backend.app.model.Contact;
import com.jskno.mykeycloak.eazybank.backend.app.repository.ContactRepository;
import com.jskno.mykeycloak.eazybank.backend.app.service.ContactService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/contact")
    public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
        return contactService.save(contact);
    }

    @PostMapping("/contacts")
    public Collection<Contact> saveContactInquiryDetails(@RequestBody List<Contact> contacts) {
        return contactService.saveAll(contacts);
    }


}
