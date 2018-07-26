package com.rl.x.a21_inaction.add_expectation.presenter;

import android.content.Context;

import com.rl.x.a21_inaction.add_expectation.AddExpectationContract;
import com.rl.x.a21_inaction.add_expectation.model.AddExpectationModel;
import com.rl.x.a21_inaction.add_expectation.model.TempExpectation;

public class AddExpectationPresenter implements AddExpectationContract.Presenter {

    private AddExpectationContract.View view;
    private AddExpectationModel model;

    public AddExpectationPresenter(Context applicationContext, AddExpectationContract.View view) {

        this.view = view;
        model = new AddExpectationModel(applicationContext);
    }

    @Override
    public void insertTempExpectation() {

        model.insertTempExpectation(new TempExpectation(view.getNewExpectationName()));
        view.finishActivity();
    }
}
