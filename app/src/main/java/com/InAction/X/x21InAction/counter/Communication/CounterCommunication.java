package com.InAction.X.x21InAction.counter.Communication;

import android.content.Context;

import com.InAction.X.x21InAction.counter.CounterContract;
import com.InAction.X.x21InAction.counter.model.CounterModel;
import com.InAction.X.x21InAction.counter.presenter.CounterPresenter;
import com.InAction.X.x21InAction.manager.AppManager;

public class CounterCommunication implements CounterContract.Communication {


    private Context context;
    private CounterModel model;


    public CounterCommunication(Context applicationContext) {

        this.context = applicationContext;
        model = new CounterModel(applicationContext);
    }


    @Override
    public int getCount() {

        return model.getCount();
    }

    @Override
    public void resetCounter() {

        model.resetCounter();
    }


    @Override
    public void startCountingIfMidnight() {

        new CounterPresenter(context).startCountingIfMidnight();
    }

    @Override
    public void stopCounter() {

        new CounterPresenter(context).stopCounter();
    }
}
