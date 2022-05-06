package com.campus02.camtacts.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campus02.camtacts.R;

import com.campus02.camtacts.model.ContactsService;
import com.campus02.camtacts.model.InMemoryContactsService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShowAllContactsActivity extends AppCompatActivity {

  RecyclerView rvContacts;
  FloatingActionButton fabAddNewContact;

  ContactsService contactsService = InMemoryContactsService.getInstance();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_show_all_contacts);

    initializeComponents();
  }

  private void initializeComponents() {
    initializeViews();

    rvContacts.setLayoutManager(new LinearLayoutManager(this));

    fabAddNewContact.setOnClickListener(this::fabAddNewContactOnClick);
  }

  private void initializeViews() {
    rvContacts = findViewById(R.id.rvContacts);
    fabAddNewContact = findViewById(R.id.fabAddNewContact);
  }

  @Override
  protected void onResume() {
    super.onResume();

    populateContacts();
  }

  private void fabAddNewContactOnClick(View __) {
    Intent intent = new Intent(this, AddOrEditContactActivity.class);
    startActivity(intent);
  }

  private void populateContacts() {
    ContactRecyclerViewAdapter adapter = new ContactRecyclerViewAdapter(contactsService.getAllContacts());
    rvContacts.setAdapter(adapter);
  }
}