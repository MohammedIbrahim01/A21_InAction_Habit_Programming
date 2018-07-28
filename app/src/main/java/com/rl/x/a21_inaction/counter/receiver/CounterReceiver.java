package com.rl.x.a21_inaction.counter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.rl.x.a21_inaction.counter.model.CounterModel;
import com.rl.x.a21_inaction.manager.AppManager;

public class CounterReceiver extends BroadcastReceiver {

    private CounterModel counterModel;
    private AppManager manager;


    @Override
    public void onReceive(Context context, Intent intent) {

        counterModel = new CounterModel(context);
        manager = new AppManager(context);

        manager.newDay();

        counterModel.increaseCount();
    }
}
