package com.InAction.X.x21InAction.counter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.InAction.X.x21InAction.counter.model.CounterModel;
import com.InAction.X.x21InAction.counter.presenter.CounterPresenter;
import com.InAction.X.x21InAction.manager.AppManager;

public class CounterReceiver extends BroadcastReceiver {

    private AppManager manager;
    private CounterModel model;

    @Override
    public void onReceive(Context context, Intent intent) {

        manager = new AppManager(context);
        model = new CounterModel(context);

        int count = model.getCount();

        if (count < 20) {

            manager.newDay();
            model.increaseCount();
        }
        else if (count == 20){

            manager.newDay();
            model.increaseCount();
            manager.stopCounter();
        }
    }
}
