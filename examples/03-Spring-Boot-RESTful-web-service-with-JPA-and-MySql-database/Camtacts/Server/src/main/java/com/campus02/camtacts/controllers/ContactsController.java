package com.campus02.camtacts.controllers;

import com.campus02.camtacts.model.contacts.dtos.ContactDto;
import com.campus02.camtacts.model.contacts.ContactsService;

import java.util.List;
import java.util.stream.Collectors;

import com.campus02.camtacts.model.contacts.dtos.NewContactDto;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/contacts")
public class ContactsController {

  private final ContactsService contactsService;

  public ContactsController(ContactsService contactsService) {
    this.contactsService = contactsService;
  }

  @GetMapping()
  public List<ContactDto> getAllContacts() {
    return contactsService.getAllContacts().stream().map(ContactDto::from).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ContactDto getContactById(@PathVariable int id) {
    return ContactDto.from(this.contactsService.getContactById(id));
  }

  @PostMapping()
  public ContactDto addNewContact(@RequestBody NewContactDto contact) {
    return ContactDto.from(this.contactsService.addNewContact(contact));
  }
}
