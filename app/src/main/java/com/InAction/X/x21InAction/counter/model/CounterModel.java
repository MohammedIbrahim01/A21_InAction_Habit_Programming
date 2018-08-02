package com.InAction.X.x21InAction.counter.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.InAction.X.x21InAction.AppCP;
import com.InAction.X.x21InAction.counter.CounterContract;

public class CounterModel implements CounterContract.Model{


    public static final String NAME_SHARED_PREFERENCES = AppCP.NAME_SHARED_PREFERENCES;
    public static final String KEY_COUNT = AppCP.KEY_COUNT;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int count;


    public CounterModel(Context applicationContext) {

        sharedPreferences = applicationContext.getSharedPreferences(NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    @Override
    public int getCount() {

        count = sharedPreferences.getInt(KEY_COUNT, 0);
        return count;
    }


    @Override
    public void increaseCount() {

        count = sharedPreferences.getInt(KEY_COUNT, 0);
        editor.putInt(KEY_COUNT, ++count).apply();
    }


    @Override
    public void resetCounter() {

        editor.putInt(KEY_COUNT, 0).apply();
    }
}