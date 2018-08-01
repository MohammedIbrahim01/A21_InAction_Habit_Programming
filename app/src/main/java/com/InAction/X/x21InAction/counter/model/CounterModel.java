package com.InAction.X.x21InAction.counter.model;

import android.content.Context;
import android.content.SharedPreferences;

public class CounterModel {

    public static final String NAME_SHARED_PREFERENCES = "name-sharedPreferences";
    public static final String KEY_COUNT = "key-count";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int count;

    public CounterModel(Context applicationContext) {

        sharedPreferences = applicationContext.getSharedPreferences(NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public int getCount(){

        count = sharedPreferences.getInt(KEY_COUNT, 0);
        return count;
    }

    public void increaseCount(){

        count = sharedPreferences.getInt(KEY_COUNT, 0);
        editor.putInt(KEY_COUNT, ++count).apply();
    }

    public void resetCounter() {

        editor.putInt(KEY_COUNT, 0).apply();
    }
}