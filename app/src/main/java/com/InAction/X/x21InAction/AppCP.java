package com.InAction.X.x21InAction;

import android.app.AlarmManager;

import java.util.Calendar;

public class AppCP {

    //AppManager
    public static final String NAME_SHARED_PREFERENCES = "name-sharedPreferences";
    public static final String KEY_FIRST_LAUNCH = "key-first_launch";
    public static final String KEY_HABIT_NAME = "key-habit-name";

    //CounterPresenter
    public static final int REQUEST_CODE_COUNTER_ALARM = 44;
    public static final int REQUEST_CODE_MIDNIGHT_ALARM = 33;
    public static final int HOUR_MIDNIGHT = 20;
    public static final int MINUTE_MIDNIGHT = 0;
    public static final int SECOND_MIDNIGHT = 0;
    public static final long INTERVAL_COUNTER = 5 * 60 * 1000;

    //CounterModel
    public static final String KEY_COUNT = "key-count";
}
