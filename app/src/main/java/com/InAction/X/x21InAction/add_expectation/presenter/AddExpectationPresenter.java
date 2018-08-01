package com.InAction.X.x21InAction.add_expectation.presenter;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.InAction.X.x21InAction.add_expectation.AddExpectationContract;
import com.InAction.X.x21InAction.add_expectation.model.AddExpectationModel;
import com.InAction.X.x21InAction.add_expectation.model.TempExpectation;
import com.InAction.X.x21InAction.add_expectation.view.TempExpectationViewModel;

public class AddExpectationPresenter implements AddExpectationContract.Presenter {

    private AddExpectationModel model;
    private AddExpectationContract.View view;

    private TempExpectationViewModel viewModel;

    public AddExpectationPresenter(Context applicationContext, AddExpectationContract.View view) {

        model = new AddExpectationModel(applicationContext);
        this.view = view;
    }

    public AddExpectationPresenter(FragmentActivity fragmentActivity){

        viewModel = ViewModelProviders.of(fragmentActivity).get(TempExpectationViewModel.class);
    }


    @Override
    public void insertTempExpectation() {

        model.insertTempExpectation(new TempExpectation(view.getExpectationName(), view.getHabitName()));
        view.finishActivity();
    }
}
