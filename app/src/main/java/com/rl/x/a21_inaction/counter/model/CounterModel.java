package com.rl.x.a21_inaction.counter.model;

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
        count = sharedPreferences.getInt(KEY_COUNT, 0);
    }

    public int getCount(){

        return count;
    }

    public void increaseCount(){

        editor.putInt(KEY_COUNT, ++count).apply();
    }
}