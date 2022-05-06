package com.campus02.camtacts.model;

import java.util.List;

public interface ContactsService {
    List<Contact> getAllContacts();
    Contact getContactById(int contactId);
    void addNewContact(Contact contact);
    void editExistingContact(Contact contact);
}
