package com.InAction.X.x21InAction.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.InAction.X.x21InAction.counter.Communication.CounterCommunication;
import com.InAction.X.x21InAction.counter.presenter.CounterPresenter;
import com.InAction.X.x21InAction.manager.AppManager;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        new CounterPresenter(context).startCountingIfMidnight();
    }
}
