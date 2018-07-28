package com.rl.x.a21_inaction.counter.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.rl.x.a21_inaction.counter.model.CounterModel;
import com.rl.x.a21_inaction.counter.receiver.CounterReceiver;

import java.util.Calendar;

public class CounterPresenter {

    private Context applicationContext;
    private CounterModel model;

//    private Calendar midnightHour;


    public CounterPresenter(Context applicationContext) {

        this.applicationContext = applicationContext;
        model = new CounterModel(applicationContext);
//        midnightHour = Calendar.getInstance();
//        midnightHour.set(Calendar.HOUR_OF_DAY, 0);
//        midnightHour.set(Calendar.MINUTE, 0);
//        midnightHour.set(Calendar.SECOND, 0);
//        midnightHour.set(Calendar.DAY_OF_YEAR, midnightHour.get(Calendar.DAY_OF_YEAR) + 1);
    }

    public void startCountingIfMidnight() {

        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(applicationContext, CounterReceiver.class);
        PendingIntent operation = PendingIntent.getBroadcast(applicationContext, 44, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 180*1000, operation);
    }
}
