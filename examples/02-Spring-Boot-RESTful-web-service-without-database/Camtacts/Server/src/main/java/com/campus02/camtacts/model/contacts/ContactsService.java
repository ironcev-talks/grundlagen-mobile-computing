package com.campus02.camtacts.model.contacts;

import com.campus02.camtacts.model.contacts.dtos.EditContactDto;
import com.campus02.camtacts.model.contacts.dtos.NewContactDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsService {

    private final ContactsRepository contactsRepository;

    public ContactsService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public List<Contact> getAllContacts() {
        return contactsRepository.getAll();
    }

    public Contact getContactById(int contactId) {
        return contactsRepository.getById(contactId);
    }

    public Contact addNewContact(NewContactDto newContact) {
        Contact contact = new Contact();

        contact.setFirstName(newContact.firstName);
        contact.setLastName(newContact.lastName);
        contact.setPhoneNumber(newContact.phoneNumber);

        return contactsRepository.save(contact);
    }

    public Contact editExistingContact(EditContactDto existingContact) {
        Contact contact = contactsRepository.getById(existingContact.id);

        contact.setFirstName(existingContact.firstName);
        contact.setLastName(existingContact.lastName);
        contact.setPhoneNumber(existingContact.phoneNumber);

        return contactsRepository.save(contact);
    }
}
