package com.rl.x.a21_inaction.counter.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.rl.x.a21_inaction.counter.receiver.CounterReceiver;
import com.rl.x.a21_inaction.counter.receiver.MidnightReceiver;
import com.rl.x.a21_inaction.habit.model.HabitModel;
import com.rl.x.a21_inaction.manager.AppManager;
import com.rl.x.a21_inaction.utils.AppNotifications;

import java.util.Calendar;

public class CounterPresenter {

    private Context applicationContext;
    private HabitModel habitModel;

    private Calendar midnight;
    private Calendar now;
    private Calendar timeToStart;


    public CounterPresenter(Context applicationContext) {

        this.applicationContext = applicationContext;
        habitModel = new HabitModel(applicationContext);

        midnight = Calendar.getInstance();
        now = Calendar.getInstance();
        timeToStart = Calendar.getInstance();
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        timeToStart.set(Calendar.MINUTE, 0);
    }

    public void startCountingIfMidnight() {

        if (now.get(Calendar.HOUR_OF_DAY) == midnight.get(Calendar.HOUR_OF_DAY)) {

            AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(applicationContext, CounterReceiver.class);
            PendingIntent operation = PendingIntent.getBroadcast(applicationContext, 44, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + AlarmManager.INTERVAL_DAY, AlarmManager.INTERVAL_DAY, operation);
        }
        else {

            midnight.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR + 1));
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
            alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + 10*1000, 10*1000, operation);
        }
        else {

            AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(applicationContext, MidnightReceiver.class);
            PendingIntent operation = PendingIntent.getBroadcast(applicationContext, 33, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.set(AlarmManager.RTC, timeToStart.getTimeInMillis(), operation);
        }
    }

    public void notifyCountingStart() {

        AppNotifications appNotifications = new AppNotifications(applicationContext);
        appNotifications.notifyCountingStart(habitModel.getHabitName());
    }
}
