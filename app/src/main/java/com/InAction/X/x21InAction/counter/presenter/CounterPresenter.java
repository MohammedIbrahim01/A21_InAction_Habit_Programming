package com.InAction.X.x21InAction.counter.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.InAction.X.x21InAction.counter.receiver.CounterReceiver;
import com.InAction.X.x21InAction.counter.receiver.HourReceiver;
import com.InAction.X.x21InAction.counter.receiver.MidnightReceiver;
import com.InAction.X.x21InAction.habit.model.HabitModel;
import com.InAction.X.x21InAction.manager.AppManager;
import com.InAction.X.x21InAction.utils.AppNotifications;

import java.util.Calendar;

public class CounterPresenter {

    public static final String KEY_HOUR_TO_START_COUNTING = "key_hour_to_start_counting";
    private Context applicationContext;
    private HabitModel habitModel;
    private AppManager manager;

    private Calendar now;
    private Calendar midnight;
    //For Testing
    private Calendar timeToStart;


    public CounterPresenter(Context applicationContext) {

        this.applicationContext = applicationContext;
        habitModel = new HabitModel(applicationContext);
        manager = new AppManager(applicationContext);

        now = Calendar.getInstance();

        midnight = Calendar.getInstance();
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);

        //For Testing
        timeToStart = Calendar.getInstance();
        timeToStart.set(Calendar.MINUTE, 0);
        timeToStart.set(Calendar.SECOND, 0);
    }

    public void startCountingIfMidnight() {

        if (now.get(Calendar.HOUR_OF_DAY) == midnight.get(Calendar.HOUR_OF_DAY)) {

            AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(applicationContext, CounterReceiver.class);
            PendingIntent operation = PendingIntent.getBroadcast(applicationContext, 44, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + AlarmManager.INTERVAL_DAY, AlarmManager.INTERVAL_DAY, operation);
            manager.startFirstDay();
        }
        else {

            //next midnight is in the next day
            midnight.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR ) + 1);

            AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(applicationContext, MidnightReceiver.class);
            PendingIntent operation = PendingIntent.getBroadcast(applicationContext, 33, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.set(AlarmManager.RTC, midnight.getTimeInMillis(), operation);
        }
    }


    /**
     * for testing
     */
    public void startCountingIf(int hour) {

        timeToStart.set(Calendar.HOUR_OF_DAY, hour);

        if (now.get(Calendar.HOUR_OF_DAY) == timeToStart.get(Calendar.HOUR_OF_DAY)) {

            AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(applicationContext, CounterReceiver.class);
            PendingIntent operation = PendingIntent.getBroadcast(applicationContext, 44, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + AlarmManager.INTERVAL_DAY, AlarmManager.INTERVAL_DAY, operation);
            manager.startFirstDay();
        }
        else {

            AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(applicationContext, HourReceiver.class);
            intent.putExtra(KEY_HOUR_TO_START_COUNTING, hour);
            PendingIntent operation = PendingIntent.getBroadcast(applicationContext, 33, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.set(AlarmManager.RTC, timeToStart.getTimeInMillis(), operation);
        }
    }

    public void notifyCountingStart() {

        AppNotifications appNotifications = new AppNotifications(applicationContext);
        appNotifications.notifyCountingStart(habitModel.getHabitName());
    }
}
