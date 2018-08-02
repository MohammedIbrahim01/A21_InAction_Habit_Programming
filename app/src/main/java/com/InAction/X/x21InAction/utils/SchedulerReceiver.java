package com.InAction.X.x21InAction.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.InAction.X.x21InAction.tasks.model.Task;

public class SchedulerReceiver extends BroadcastReceiver{

    public static final String KEY_TASK = "key-task";

    @Override
    public void onReceive(Context context, Intent intent) {

        Task task = new Gson().fromJson(intent.getStringExtra(KEY_TASK), Task.class);

        NotificationsUtils notificationsUtils = new NotificationsUtils(context);
        notificationsUtils.notifyTimeFor(task);
    }
}
