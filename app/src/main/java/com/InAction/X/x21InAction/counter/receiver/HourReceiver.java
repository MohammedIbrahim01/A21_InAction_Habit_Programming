package com.InAction.X.x21InAction.counter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.InAction.X.x21InAction.counter.presenter.CounterPresenter;

import java.util.Calendar;

public class HourReceiver extends BroadcastReceiver {

    public static final String KEY_HOUR_TO_START_COUNTING = "key_hour_to_start_counting";

    private CounterPresenter counterPresenter;

    @Override
    public void onReceive(Context context, Intent intent) {

        int hour = 0;

        if (intent.hasExtra(KEY_HOUR_TO_START_COUNTING)) {

            hour = intent.getIntExtra(KEY_HOUR_TO_START_COUNTING, 0);
        }

        counterPresenter = new CounterPresenter(context);

        counterPresenter.startCountingIf(hour);

        counterPresenter.notifyCountingStart();
    }
}
