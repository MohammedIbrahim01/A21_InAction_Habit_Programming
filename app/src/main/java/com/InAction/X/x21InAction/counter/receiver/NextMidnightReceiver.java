package com.InAction.X.x21InAction.counter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.InAction.X.x21InAction.counter.presenter.CounterPresenter;

public class NextMidnightReceiver extends BroadcastReceiver {

    private CounterPresenter counterPresenter;

    @Override
    public void onReceive(Context context, Intent intent) {

        counterPresenter = new CounterPresenter(context);

        counterPresenter.startCountingIfMidnight();
    }
}
