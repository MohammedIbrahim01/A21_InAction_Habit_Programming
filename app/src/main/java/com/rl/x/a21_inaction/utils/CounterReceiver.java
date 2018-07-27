package com.rl.x.a21_inaction.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.rl.x.a21_inaction.counter.model.CounterModel;
import com.rl.x.a21_inaction.manager.AppManager;

public class CounterReceiver extends BroadcastReceiver {

    private AppManager manager;
    private CounterModel counterModel;

    @Override
    public void onReceive(Context context, Intent intent) {

        counterModel = new CounterModel(context);
        manager = new AppManager(context);

        manager.newDay(counterModel.getCount());

        counterModel.increaseCount();
    }
}
