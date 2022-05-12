package com.campus02.camtacts.model.contacts;

import com.campus02.camtacts.model.contacts.dtos.EditContactDto;
import com.campus02.camtacts.model.contacts.dtos.NewContactDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContactsService {

    private final ContactsRepository contactsRepository;

    public ContactsService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public List<Contact> getAllContacts() {
        return StreamSupport
                .stream(this.contactsRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Contact getContactById(int contactId) {
        // This is a happy path programming at the moment. So we do not check for .isPresent()
        // and just call .get();
        //noinspection OptionalGetWithoutIsPresent
        return this.contactsRepository.findById(contactId).get();
    }

    public Contact addNewContact(NewContactDto newContact) {
        Contact contact = new Contact();

        contact.setFirstName(newContact.firstName);
        contact.setLastName(newContact.lastName);
        contact.setPhoneNumber(newContact.phoneNumber);

        this.contactsRepository.save(contact);

        return contact;
    }

    public Contact editExistingContact(EditContactDto existingContact) {
        //noinspection OptionalGetWithoutIsPresent
        Contact contact = this.contactsRepository.findById(existingContact.id).get();

        contact.setFirstName(existingContact.firstName);
        contact.setLastName(existingContact.lastName);
        contact.setPhoneNumber(existingContact.phoneNumber);

        this.contactsRepository.save(contact);

        return contact;
    }
}
