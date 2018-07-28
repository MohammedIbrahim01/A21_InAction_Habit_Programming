package com.rl.x.a21_inaction.counter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.rl.x.a21_inaction.counter.presenter.CounterPresenter;

public class MidnightReceiver extends BroadcastReceiver{

    private CounterPresenter counterPresenter;

    @Override
    public void onReceive(Context context, Intent intent) {

        counterPresenter = new CounterPresenter(context);

        counterPresenter.startCountingIfMidnight();

        counterPresenter.notifyCountingStart();
    }
}
