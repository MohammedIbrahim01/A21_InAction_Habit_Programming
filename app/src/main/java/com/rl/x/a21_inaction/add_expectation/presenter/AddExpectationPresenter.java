package com.rl.x.a21_inaction.add_expectation.presenter;

import android.content.Context;

import com.rl.x.a21_inaction.add_expectation.AddExpectationContract;
import com.rl.x.a21_inaction.add_expectation.model.AddExpectationModel;
import com.rl.x.a21_inaction.add_expectation.model.TempExpectation;

public class AddExpectationPresenter implements AddExpectationContract.Presenter {

    private AddExpectationModel model;
    private AddExpectationContract.View view;

    public AddExpectationPresenter(Context applicationContext, AddExpectationContract.View view) {

        model = new AddExpectationModel(applicationContext);
        this.view = view;
    }

    @Override
    public void insertTempExpectation() {

        model.insertTempExpectation(new TempExpectation(view.getExpectationName(), view.getHabitName()));
        view.finishActivity();
    }
}
