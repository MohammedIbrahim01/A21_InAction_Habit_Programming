package com.rl.x.a21_inaction.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.rl.x.a21_inaction.tasks.model.Task;

public class Scheduler {

    public static final String KEY_TASK = "key-task";

    private int baseRequestCode = 100;

    private Context applicationContext;
    private AlarmManager alarmManager;

    public Scheduler(Context applicationContext) {

        this.applicationContext = applicationContext;
        alarmManager = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);
    }

    public void scheduleTask(Task task) {

        Intent intent = new Intent(applicationContext.getApplicationContext(), SchedulerReceiver.class);
        intent.putExtra(KEY_TASK, new Gson().toJson(task));

        int requestCode = ++baseRequestCode;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(applicationContext, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        task.setScheduleRequestCode(requestCode);

        alarmManager.set(AlarmManager.RTC, task.getCalendar().getTimeInMillis(), pendingIntent);
    }

    public void unScheduleTask(Task task) {

        Intent intent = new Intent(applicationContext.getApplicationContext(), SchedulerReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(applicationContext, task.getScheduleRequestCode(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
    }
}
