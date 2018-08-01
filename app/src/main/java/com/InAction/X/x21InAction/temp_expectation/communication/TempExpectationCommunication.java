package com.InAction.X.x21InAction.temp_expectation.communication;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.InAction.X.x21InAction.temp_expectation.TempExpectationContract;
import com.InAction.X.x21InAction.temp_expectation.model.TempExpectation;
import com.InAction.X.x21InAction.temp_expectation.presenter.TempExpectationPresenter;

import java.util.List;

public class TempExpectationCommunication implements TempExpectationContract.Communication {


    private TempExpectationPresenter presenter;


    public TempExpectationCommunication(Context applicationContext) {

        presenter = new TempExpectationPresenter(applicationContext);
    }


    @Override
    public List<TempExpectation> getTempExpectationList() {

        return presenter.getTempExpectationList();
    }


    @Override
    public LiveData<List<TempExpectation>> getTempExpectationListLive() {

        return presenter.getTempExpectationListLive();
    }


    @Override
    public void deleteAllTempExpectations() {

        presenter.deleteAllTempExpectations();
    }
}
