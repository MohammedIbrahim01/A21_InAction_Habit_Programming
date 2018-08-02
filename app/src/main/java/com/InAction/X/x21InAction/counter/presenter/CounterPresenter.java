package com.InAction.X.x21InAction.counter.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.InAction.X.x21InAction.AppCP;
import com.InAction.X.x21InAction.counter.CounterContract;
import com.InAction.X.x21InAction.counter.model.CounterModel;
import com.InAction.X.x21InAction.counter.receiver.CounterReceiver;
import com.InAction.X.x21InAction.counter.receiver.NextMidnightReceiver;
import com.InAction.X.x21InAction.habit.model.HabitModel;
import com.InAction.X.x21InAction.manager.AppManager;
import com.InAction.X.x21InAction.utils.NotificationsUtils;

import java.util.Calendar;

public class CounterPresenter implements CounterContract.Presenter {


    public static final int REQUEST_CODE_COUNTER_ALARM = AppCP.REQUEST_CODE_COUNTER_ALARM;
    public static final int REQUEST_CODE_MIDNIGHT_ALARM = AppCP.REQUEST_CODE_MIDNIGHT_ALARM;
    public static final int HOUR_MIDNIGHT = AppCP.HOUR_MIDNIGHT;
    public static final int MINUTE_MIDNIGHT = AppCP.MINUTE_MIDNIGHT;
    public static final int SECOND_MIDNIGHT = AppCP.SECOND_MIDNIGHT;
    private static final long INTERVAL_COUNTER = AppCP.INTERVAL_COUNTER;

    private Context applicationContext;
    private AlarmManager alarmManager;
    private CounterModel model;

    private Calendar midnight;


    public CounterPresenter(Context applicationContext) {

        this.applicationContext = applicationContext;
        model = new CounterModel(applicationContext);
        alarmManager = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);


        //set midnight fields
        midnight = Calendar.getInstance();
        midnight.set(Calendar.HOUR_OF_DAY, HOUR_MIDNIGHT);
        midnight.set(Calendar.MINUTE, MINUTE_MIDNIGHT);
        midnight.set(Calendar.SECOND, SECOND_MIDNIGHT);
    }


    @Override
    public void startCountingIfMidnight(AppManager manager) {

        Calendar now = Calendar.getInstance();


        //check if now is in midnight hour
        if (now.get(Calendar.HOUR_OF_DAY) == midnight.get(Calendar.HOUR_OF_DAY)) {

            Intent intent = new Intent(applicationContext, CounterReceiver.class);
            PendingIntent operation = PendingIntent.getBroadcast(applicationContext, REQUEST_CODE_COUNTER_ALARM, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + INTERVAL_COUNTER, INTERVAL_COUNTER, operation);

            notifyCountingStart(manager.getHabitName());
            manager.startFirstDay();

        } else {

            //next midnight is in the next day (paused)
            midnight.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR));

            Intent intent = new Intent(applicationContext, NextMidnightReceiver.class);
            PendingIntent operation = PendingIntent.getBroadcast(applicationContext, REQUEST_CODE_MIDNIGHT_ALARM, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.set(AlarmManager.RTC, midnight.getTimeInMillis(), operation);
        }
    }


    @Override
    public void notifyCountingStart(String habitName) {

        NotificationsUtils notificationsUtils = new NotificationsUtils(applicationContext);
        notificationsUtils.notifyCountingStart(habitName);
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
