package com.campus02.camtacts.model.contacts.dtos;

import com.campus02.camtacts.model.contacts.Contact;

public class ContactDto extends BaseContactDto {

  public int id;

  public static ContactDto from(Contact contact) {
    ContactDto result = new ContactDto();

    result.id = contact.getId();
    result.firstName = contact.getFirstName();
    result.lastName = contact.getLastName();
    result.phoneNumber = contact.getPhoneNumber();

    return result;
  }
}
