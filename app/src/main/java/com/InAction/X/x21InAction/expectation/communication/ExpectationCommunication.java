package com.InAction.X.x21InAction.expectation.communication;

import android.content.Context;

import com.InAction.X.x21InAction.expectation.ExpectationContract;
import com.InAction.X.x21InAction.expectation.model.Expectation;
import com.InAction.X.x21InAction.expectation.presenter.ExpectationPresenter;

import java.util.List;

public class ExpectationCommunication implements ExpectationContract.Communication {


    private ExpectationPresenter presenter;


    public ExpectationCommunication(Context applicationContext) {

        presenter = new ExpectationPresenter(applicationContext);
    }


    @Override
    public void insertExpectationList(List<Expectation> expectationList) {

        presenter.insertExpectationList(expectationList);
    }
}
