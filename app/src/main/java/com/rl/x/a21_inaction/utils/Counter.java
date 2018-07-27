package com.rl.x.a21_inaction.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class Counter {


    private Context applicationContext;


    public Counter(Context applicationContext) {

        this.applicationContext = applicationContext;
    }

    public void beginDayCounter() {

        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);
        PendingIntent operation = PendingIntent.getBroadcast(applicationContext, 44, new Intent(applicationContext, CounterReceiver.class), PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 180*1000, operation);
    }
}
