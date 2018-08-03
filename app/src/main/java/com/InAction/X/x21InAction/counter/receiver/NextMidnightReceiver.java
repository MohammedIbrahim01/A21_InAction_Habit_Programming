package com.InAction.X.x21InAction.counter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.InAction.X.x21InAction.counter.presenter.CounterPresenter;
import com.InAction.X.x21InAction.manager.AppManager;
import com.google.gson.Gson;

public class NextMidnightReceiver extends BroadcastReceiver {

    public static final String KEY_APP_MANAGER = "key-app-manager";
    private CounterPresenter counterPresenter;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("WWW", "onReceive: counterReceiver");

        counterPresenter = new CounterPresenter(context);

        counterPresenter.startCountingIfMidnight();
    }
}
