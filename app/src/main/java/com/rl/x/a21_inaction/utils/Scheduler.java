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

    private Context context;
    private AlarmManager alarmManager;

    public Scheduler(Context context) {

        this.context = context;
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public void scheduleTask(Task task) {

        Intent intent = new Intent(context.getApplicationContext(), SchedulerReceiver.class);
        intent.putExtra(KEY_TASK, new Gson().toJson(task));

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, baseRequestCode++, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        alarmManager.set(AlarmManager.RTC, task.getCalendar().getTimeInMillis(), pendingIntent);
    }
}
