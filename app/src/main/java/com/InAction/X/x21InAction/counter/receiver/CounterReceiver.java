package com.InAction.X.x21InAction.counter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.InAction.X.x21InAction.counter.presenter.CounterPresenter;
import com.InAction.X.x21InAction.manager.AppManager;

public class CounterReceiver extends BroadcastReceiver {

    private AppManager manager;
    private CounterPresenter presenter;


    @Override
    public void onReceive(Context context, Intent intent) {

        manager = new AppManager(context);
        presenter = new CounterPresenter(context);


        manager.newDay();
        presenter.increaseCount();
    }
}
