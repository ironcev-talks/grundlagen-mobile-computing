package com.campus02.camtacts.model.contacts;

import java.util.List;

public interface ContactsRepository {
    Contact save(Contact contact);
    List<Contact> getAll();
    Contact getById(int id);
}