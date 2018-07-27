package com.rl.x.a21_inaction.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rl.x.a21_inaction.tasks.model.Task;

public class SchedulerReceiver extends BroadcastReceiver{

    public static final String KEY_TASK = "key-task";

    @Override
    public void onReceive(Context context, Intent intent) {

        Task task = new Gson().fromJson(intent.getStringExtra(KEY_TASK), Task.class);

        AppNotifications appNotifications = new AppNotifications(context);
        appNotifications.notifyWith(task);

        Toast.makeText(context, "onReceive: Task name : " + task.getName(), Toast.LENGTH_SHORT).show();
        Log.i("WWW", "onReceive: Task name");
    }
}
