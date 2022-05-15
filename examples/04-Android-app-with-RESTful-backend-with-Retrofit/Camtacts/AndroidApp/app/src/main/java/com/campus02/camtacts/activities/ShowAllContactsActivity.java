package com.campus02.camtacts.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campus02.camtacts.R;

import com.campus02.camtacts.model.Contact;
import com.campus02.camtacts.model.ContactsService;
import com.campus02.camtacts.model.RetrofitContactsServiceBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllContactsActivity extends AppCompatActivity {

  RecyclerView rvContacts;
  FloatingActionButton fabAddNewContact;

  ContactsService contactsService = RetrofitContactsServiceBuilder.getContactsService();

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
    contactsService
        .getAllContacts()
        .enqueue(new Callback<List<Contact>>() {
          @Override
          public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
            createAndSetContactAdapter(response.body());
          }

          @Override
          public void onFailure(Call<List<Contact>> call, Throwable t) {
            Toast.makeText(ShowAllContactsActivity.this, "Ups! Something went wrong :-(\nFailed to load contacts.", Toast.LENGTH_SHORT).show();
            createAndSetContactAdapter(new ArrayList<>());
          }

          private void createAndSetContactAdapter(List<Contact> contacts) {
            ContactRecyclerViewAdapter contactAdapter = new ContactRecyclerViewAdapter(contacts);
            rvContacts.setAdapter(contactAdapter);
          }
    });
  }
}