package com.campus02.camtacts.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactsService {
    @GET("/contacts")
    Call<List<Contact>> getAllContacts();

    @GET("/contacts/{id}")
    Call<Contact> getContactById(@Path("id") int id);

    @POST("/contacts")
    Call<Contact> addNewContact(@Body Contact contact);

    @PUT("/contacts")
    Call<Contact> editExistingContact(@Body Contact contact);
}
