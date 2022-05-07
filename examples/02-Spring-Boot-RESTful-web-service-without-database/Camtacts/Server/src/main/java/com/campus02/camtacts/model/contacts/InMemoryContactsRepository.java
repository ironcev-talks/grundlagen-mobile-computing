package com.campus02.camtacts.model.contacts;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryContactsRepository implements ContactsRepository {
    private final List<Contact> contacts = new ArrayList<>();

    public InMemoryContactsRepository() {
        createSampleContacts();
    }

    @Override
    public List<Contact> getAll() {
        return contacts;
    }

    @Override
    public Contact getById(int id) {
        // This is a happy path programming at the moment. So we do not check for .isPresent()
        // and just call .get();
        //noinspection OptionalGetWithoutIsPresent
        return contacts.stream().filter(contact -> contact.getId() == id).findFirst().get();
    }

    @Override
    public Contact save(Contact contact) {
        //noinspection StatementWithEmptyBody
        if (contact.getId() == 0) {
            contact.setId(generateNextContactId());
            this.contacts.add(contact);
        }
        else {
            // In this dummy implementation of the ContactRepository we know that
            // the changed contact we get is the one from the contacts list.
            // So it is already changed in the contact list and there is nothing
            // we need to do in this method.
        }

        return contact;
    }

    private int generateNextContactId() {
        return contacts.stream().mapToInt(Contact::getId).max().orElse(0) + 1;
    }

    private void createSampleContacts() {
        Contact elfriedeJelinek = new Contact();
        elfriedeJelinek.setFirstName("Elfriede");
        elfriedeJelinek.setLastName("Jelinek");
        elfriedeJelinek.setPhoneNumber("+43 650 11 22 334");

        Contact ivoAndric = new Contact();
        ivoAndric.setFirstName("Ivo");
        ivoAndric.setLastName("Andrić");
        ivoAndric.setPhoneNumber("+387 65 123 456");

        save(elfriedeJelinek);
        save(ivoAndric);
    }
}
