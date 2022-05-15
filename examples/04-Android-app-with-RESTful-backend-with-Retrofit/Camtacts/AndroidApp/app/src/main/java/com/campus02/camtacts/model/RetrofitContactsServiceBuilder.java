package com.campus02.camtacts.model;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitContactsServiceBuilder {
    private final ContactsService contactsService;

    private RetrofitContactsServiceBuilder() {
        // At the moment we assume that we develop only in a simulator and that the
        // backend server and the simulator run on the same machine.
        // See: https://developer.android.com/studio/run/emulator-networking
        contactsService = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build()
                .create(ContactsService.class);
    }

    private static RetrofitContactsServiceBuilder instance;
    public static ContactsService getContactsService() {
        if (instance == null) instance = new RetrofitContactsServiceBuilder();
        return instance.contactsService;
    }
}
