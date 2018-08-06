package com.example.ikn.brewdogbeer.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrewDogService {
    private static final String BASE_URL = "https://api.punkapi.com/v2/";
    public static final int PAGE_SIZE = 30;

    private static BrewDogApi instance;

    private BrewDogService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        instance = retrofit.create(BrewDogApi.class);
    }

    public static BrewDogApi getInstance() {
        if (instance == null) {
            new BrewDogService();
        }

        return instance;
    }

}
