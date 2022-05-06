package com.campus02.camtacts.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.campus02.camtacts.R;
import com.campus02.camtacts.model.Contact;
import com.campus02.camtacts.model.ContactsService;
import com.campus02.camtacts.model.InMemoryContactsService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddOrEditContactActivity extends AppCompatActivity {

  TextInputEditText txtFirstName;
  TextInputEditText txtLastName;
  TextInputEditText txtPhoneNumber;
  MaterialButton btnSaveContact;

  ContactsService contactsService = InMemoryContactsService.getInstance();

  Contact contact;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_add_or_edit_contact);

    initializeComponents();
  }

  private void initializeComponents() {
    initializeViews();

    int contactId = getIntent().getIntExtra(IntentExtras.CONTACT_ID, 0);
    boolean contactAlreadyExists = contactId != 0;

    setTitle(contactAlreadyExists ? R.string.edit_contact : R.string.add_new_contact);

    contact = contactAlreadyExists ? contactsService.getContactById(contactId) : new Contact();

    btnSaveContact.setOnClickListener(contactAlreadyExists
            ? this::btnSaveContactOnClickWhenEditExistingContact
            : this::btnSaveContactOnClickWhenAddNewContact);

    populateFormFromContact();
  }

  private void initializeViews() {
    txtFirstName = findViewById(R.id.txtFirstName);
    txtLastName = findViewById(R.id.txtLastName);
    txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
    btnSaveContact = findViewById(R.id.btnSaveContact);
  }

  private void btnSaveContactOnClickWhenEditExistingContact(View __) {
    populateContactFromForm();

    contactsService.editExistingContact(contact);

    Toast.makeText(AddOrEditContactActivity.this, "Contact successfully updated :-)", Toast.LENGTH_SHORT).show();

    finish();
  }

  private void btnSaveContactOnClickWhenAddNewContact(View __) {
    populateContactFromForm();

    contactsService.addNewContact(contact);

    Toast.makeText(AddOrEditContactActivity.this, "Contact successfully added :-)\nAdd another one if you wish or go back.", Toast.LENGTH_SHORT).show();

    cleanForm();

    contact = new Contact();
  }

  private void populateFormFromContact() {
    txtFirstName.setText(contact.getFirstName());
    txtLastName.setText(contact.getLastName());
    txtPhoneNumber.setText(contact.getPhoneNumber());
  }

  private void populateContactFromForm() {
    contact.setFirstName(String.valueOf(txtFirstName.getText()));
    contact.setLastName(String.valueOf(txtLastName.getText()));
    contact.setPhoneNumber(String.valueOf(txtPhoneNumber.getText()));
  }

  private void cleanForm() {
    txtFirstName.setText("");
    txtLastName.setText("");
    txtPhoneNumber.setText("");

    txtFirstName.requestFocus();
  }
}