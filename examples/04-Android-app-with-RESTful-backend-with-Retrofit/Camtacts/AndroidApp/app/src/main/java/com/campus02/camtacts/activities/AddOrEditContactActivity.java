package com.campus02.camtacts.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.campus02.camtacts.R;
import com.campus02.camtacts.model.Contact;
import com.campus02.camtacts.model.ContactsService;
import com.campus02.camtacts.model.RetrofitContactsServiceBuilder;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOrEditContactActivity extends AppCompatActivity {

  TextInputEditText txtFirstName;
  TextInputEditText txtLastName;
  TextInputEditText txtPhoneNumber;
  MaterialButton btnSaveContact;

  ContactsService contactsService = RetrofitContactsServiceBuilder.getContactsService();

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

    if (contactAlreadyExists) { disableForm(); } else { enableForm(); }

    if (contactAlreadyExists) {
      contactsService.getContactById(contactId).enqueue(getContactByIdCallback());
    }
    else {
      contact = new Contact();
      populateFormFromContact();
    }

    btnSaveContact.setOnClickListener(contactAlreadyExists
            ? this::btnSaveContactOnClickWhenEditExistingContact
            : this::btnSaveContactOnClickWhenAddNewContact);
  }

  private Callback<Contact> getContactByIdCallback() {
      return new Callback<Contact>() {
        @Override
        public void onResponse(Call<Contact> call, Response<Contact> response) {
          contact = response.body();
          populateFormFromContact();
          enableForm();
        }

        @Override
        public void onFailure(Call<Contact> call, Throwable t) {
          Toast.makeText(AddOrEditContactActivity.this, "Ups! Something went wrong :-(\nFailed to load contact.", Toast.LENGTH_SHORT).show();
          contact = new Contact();
        }
      };
  }

  private void initializeViews() {
    txtFirstName = findViewById(R.id.txtFirstName);
    txtLastName = findViewById(R.id.txtLastName);
    txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
    btnSaveContact = findViewById(R.id.btnSaveContact);
  }

  private void enableForm() { enableOrDisableForm(true); }
  private void disableForm() { enableOrDisableForm(false); }

  private void enableOrDisableForm(boolean enable) {
    btnSaveContact.setEnabled(enable);
    enableOrDisableEditText(txtFirstName, enable);
    enableOrDisableEditText(txtLastName, enable);
    enableOrDisableEditText(txtPhoneNumber, enable);
  }

  private static void enableOrDisableEditText(EditText editText, boolean enable) {
    editText.setInputType(enable ? InputType.TYPE_CLASS_TEXT : InputType.TYPE_NULL);
    editText.setFocusable(enable);
    editText.setFocusableInTouchMode(enable);
  }

  private void btnSaveContactOnClickWhenEditExistingContact(View __) {
    populateContactFromForm();
    disableForm();

    contactsService
      .editExistingContact(contact)
      .enqueue(new Callback<Contact>() {
        @Override
        public void onResponse(Call<Contact> call, Response<Contact> response) {
          Toast.makeText(AddOrEditContactActivity.this, "Contact successfully updated :-)", Toast.LENGTH_SHORT).show();
          finish();
        }

        @Override
        public void onFailure(Call<Contact> call, Throwable t) {
          Toast.makeText(AddOrEditContactActivity.this, "Ups! Something went wrong :-(\nFailed to save contact.", Toast.LENGTH_SHORT).show();
          enableForm();
        }
      });
  }

  private void btnSaveContactOnClickWhenAddNewContact(View __) {
    populateContactFromForm();
    disableForm();

    contactsService
      .addNewContact(contact)
      .enqueue(new Callback<Contact>() {
        @Override
        public void onResponse(Call<Contact> call, Response<Contact> response) {
          Toast.makeText(AddOrEditContactActivity.this, "Contact successfully added :-)\nAdd another one if you wish, or go back.", Toast.LENGTH_SHORT).show();
          enableForm();
          cleanForm();
          contact = new Contact();
        }

        @Override
        public void onFailure(Call<Contact> call, Throwable t) {
          Toast.makeText(AddOrEditContactActivity.this, "Ups! Something went wrong :-(\nFailed to add contact.", Toast.LENGTH_SHORT).show();
          enableForm();
        }
      });
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