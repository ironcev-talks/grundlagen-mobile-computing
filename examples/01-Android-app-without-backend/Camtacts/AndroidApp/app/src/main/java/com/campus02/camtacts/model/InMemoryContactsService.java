package com.campus02.camtacts.model;

import java.util.ArrayList;
import java.util.List;

public class InMemoryContactsService implements ContactsService {

    private final List<Contact> contacts = new ArrayList<>();

    private InMemoryContactsService() {
        createSampleContacts();
    }

    @Override
    public List<Contact> getAllContacts() {
        return contacts;
    }

    @Override
    public Contact getContactById(int contactId) {
        // This is a happy path programming at the moment. So we do not check for for .isPresent()
        // and just call .get();
        //noinspection OptionalGetWithoutIsPresent
        return contacts.stream().filter(contact -> contact.getId() == contactId).findFirst().get();
    }

    @Override
    public void addNewContact(Contact contact) {
        contact.setId(generateNextContactId());
        contacts.add(contact);
    }

    @Override
    public void editExistingContact(Contact contact) {
        // In this dummy implementation of the ContactService we know that
        // the changed contact we get is the one from the contacts list.
        // So it is already changed in the contact list and there is nothing
        // we need to do in this method.
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

        addNewContact(elfriedeJelinek);
        addNewContact(ivoAndric);
    }

    private static InMemoryContactsService instance;
    public static InMemoryContactsService getInstance() {
        if (instance == null) instance = new InMemoryContactsService();
        return instance;
    }
}
