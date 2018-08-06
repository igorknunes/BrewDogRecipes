package com.example.ikn.brewdogbeer.Util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesManager  {
    public static final String PREFERENCES_NAME = "SHARED_PREFERENCES";

    private static SharedPreferences sharedPreferences;

    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
    }

    public static void save(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, value);

        editor.apply();
    }

    public static String fetch(String key) {
        return sharedPreferences.getString(key, null);
    }

    public static void clear(String key) {
        sharedPreferences.edit().remove(key).apply();
    }
}
