package com.example.ikn.brewdogbeer;

import android.app.Application;

import com.example.ikn.brewdogbeer.Util.FavoritesManager;
import com.example.ikn.brewdogbeer.Util.SharedPreferencesManager;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesManager.init(this);
        FavoritesManager.fetchAll();
    }
}
