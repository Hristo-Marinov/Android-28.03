package com.example.android_lesson_1;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

public class SharedPreferencesManager {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String DATA_KEY = "data";
    private static final String BOOKS_JSON_KEY = "booksJson" ;

    private SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveData(JSONArray data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DATA_KEY, data.toString());
        editor.apply();
    }
    public void saveBooksJson(String json) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BOOKS_JSON_KEY, json);
        editor.apply();
    }

    public JSONArray getData() {
        try {
            String jsonString = sharedPreferences.getString(DATA_KEY, "[]");
            return new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray(); // Return an empty array in case of JSON parsing error
        }
    }
}
