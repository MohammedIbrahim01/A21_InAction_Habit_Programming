package com.rl.x.a21_inaction.counter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.rl.x.a21_inaction.counter.presenter.CounterPresenter;

import java.util.Calendar;

public class HourReceiver extends BroadcastReceiver {

    private CounterPresenter counterPresenter;

    @Override
    public void onReceive(Context context, Intent intent) {

        counterPresenter = new CounterPresenter(context);

        counterPresenter.startCountingIf(16);

        counterPresenter.notifyCountingStart();
    }
}
