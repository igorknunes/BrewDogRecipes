package com.example.ikn.brewdogbeer.Util;

import com.example.ikn.brewdogbeer.model.BeerModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class FavoritesManager {
    public static final String FAVORITES_KEY = "favorites";
    public static final String FAVORITE_LIST_KEY = "favoriteListKey";
    private static List<BeerModel> favoriteBeers;

    public static void fetchAll() {
        String favoritesString = SharedPreferencesManager.fetch(FAVORITES_KEY);

        if (favoritesString != null) {
            favoriteBeers = new Gson().fromJson(favoritesString, new TypeToken<List<BeerModel>>() {
            }.getType());
        } else {
            favoriteBeers = new ArrayList<>();
        }
    }

    public static void saveAll() {
        String favoritesString = new Gson().toJson(favoriteBeers);
        SharedPreferencesManager.save(FAVORITES_KEY, favoritesString);
    }

    public static void removeOne(BeerModel beer) {
        favoriteBeers.remove(beer);
        saveAll();
    }

    public static void addOne(BeerModel beer) {
        favoriteBeers.add(beer);
        saveAll();
    }

    public static void clear() {
        favoriteBeers = new ArrayList<>();
        SharedPreferencesManager.clear(FAVORITES_KEY);
    }

    public static boolean isFavorite(BeerModel beer) {
        return favoriteBeers.contains(beer);
    }

    public static List<BeerModel> getFavoriteBeers() {
        return favoriteBeers;
    }
}
