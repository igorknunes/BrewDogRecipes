package com.example.ikn.brewdogbeer.api;

import com.example.ikn.brewdogbeer.model.BeerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BrewDogApi {

    @GET("beers")
    Call<List<BeerModel>> getBeers(
            @Query("page") int page,
            @Query("per_page") int perPage);

    @GET("beers")
    Call<List<BeerModel>> getBeersByName(
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Query("beer_name") String beerName);

    @GET("beers/{id}")
    Call<List<BeerModel>> getBeerById(@Path("id") int id);

    @GET("beers/random")
    Call<List<BeerModel>> getRandomBeer();

}
