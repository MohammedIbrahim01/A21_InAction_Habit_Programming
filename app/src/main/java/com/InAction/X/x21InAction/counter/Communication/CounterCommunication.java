package com.InAction.X.x21InAction.counter.Communication;

import android.content.Context;

import com.InAction.X.x21InAction.counter.CounterContract;
import com.InAction.X.x21InAction.counter.presenter.CounterPresenter;

public class CounterCommunication implements CounterContract.Communication {


    private CounterPresenter presenter;


    public CounterCommunication(Context applicationContext) {

        presenter = new CounterPresenter(applicationContext);
    }


    @Override
    public int getCount() {

        return presenter.getCount();
    }

    @Override
    public void resetCounter() {

        presenter.resetCounter();
    }
}
