package com.InAction.X.x21InAction.counter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.InAction.X.x21InAction.counter.presenter.CounterPresenter;
import com.InAction.X.x21InAction.manager.AppManager;
import com.google.gson.Gson;

public class NextMidnightReceiver extends BroadcastReceiver {

    public static final String KEY_APP_MANAGER = "key-app-manager";
    private CounterPresenter counterPresenter;

    @Override
    public void onReceive(Context context, Intent intent) {

        counterPresenter = new CounterPresenter(context);
        AppManager manager = new Gson().fromJson(intent.getStringExtra(KEY_APP_MANAGER), AppManager.class);

        counterPresenter.startCountingIfMidnight(manager);
    }
}
