package com.InAction.X.x21InAction.counter.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.InAction.X.x21InAction.counter.CounterContract;
import com.InAction.X.x21InAction.counter.model.CounterModel;
import com.InAction.X.x21InAction.counter.receiver.CounterReceiver;
import com.InAction.X.x21InAction.counter.receiver.NextMidnightReceiver;
import com.InAction.X.x21InAction.habit.model.HabitModel;
import com.InAction.X.x21InAction.manager.AppManager;
import com.InAction.X.x21InAction.utils.NotificationsUtils;

import java.util.Calendar;

public class CounterPresenter implements CounterContract.Presenter {

    public static final String KEY_HOUR_TO_START_COUNTING = "key_hour_to_start_counting";

    private Context applicationContext;
    private AppManager manager;
    private AlarmManager alarmManager;
    private CounterModel model;

    private Calendar midnight;


    public CounterPresenter(Context applicationContext) {

        this.applicationContext = applicationContext;
        manager = new AppManager(applicationContext);
        model = new CounterModel(applicationContext);
        alarmManager = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);


        //set midnight fields
        midnight = Calendar.getInstance();
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);
    }


    @Override
    public void startCountingIfMidnight() {

        Calendar now = Calendar.getInstance();

        //check if now is in midnight hour
        if (now.get(Calendar.HOUR_OF_DAY) == midnight.get(Calendar.HOUR_OF_DAY)) {

            Intent intent = new Intent(applicationContext, CounterReceiver.class);
            PendingIntent operation = PendingIntent.getBroadcast(applicationContext, 44, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + AlarmManager.INTERVAL_DAY, AlarmManager.INTERVAL_DAY, operation);
            notifyCountingStart();
            manager.startFirstDay();
        } else {

            //next midnight is in the next day
            midnight.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR) + 1);

            Intent intent = new Intent(applicationContext, NextMidnightReceiver.class);
            PendingIntent operation = PendingIntent.getBroadcast(applicationContext, 33, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.set(AlarmManager.RTC, midnight.getTimeInMillis(), operation);
        }
    }


    @Override
    public void notifyCountingStart() {

        NotificationsUtils notificationsUtils = new NotificationsUtils(applicationContext);
        notificationsUtils.notifyCountingStart(manager.getHabitName());
    }


    @Override
    public void increaseCount() {

        model.increaseCount();
    }


    @Override
    public int getCount() {

        return model.getCount();
    }


    @Override
    public void resetCounter() {

        model.resetCounter();
    }
}
