package com.rl.x.a21_inaction.day_zero.presenter;

import android.content.Context;

import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.day_zero.ExpectationContract;
import com.rl.x.a21_inaction.day_zero.model.Expectation;
import com.rl.x.a21_inaction.day_zero.model.ExpectationDao;
import com.rl.x.a21_inaction.day_zero.model.ExpectationModel;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.List;
import java.util.concurrent.Executor;

public class ExpectationPresenter implements ExpectationContract.Presenter {

    private ExpectationContract.View view;
    private ExpectationModel model;


    public ExpectationPresenter(Context applicationContext, ExpectationContract.View view) {

        this.view = view;
        model = new ExpectationModel(applicationContext);
    }

    /**
     * setup recyclerView with adapter
     */
    @Override
    public void setupRecyclerViewWithAdapter() {
        view.setupRecyclerViewWithAdapter();
    }


    /**
     * retrieve Expectations from database then display it
     */
    @Override
    public void retrieveAndDisplayExpectations() {

        view.displayExpectations(model.retrieveExpectations());
    }


    /**
     * insert expectation to database
     *
     * @param name
     */
    @Override
    public void insertExpectation(String name) {

        model.insertExpectation(new Expectation(name));
    }


    /**
     * insert mock Expectations
     */
    @Override
    public void insertMockExpectations() {

        model.insertMockExpectation();
    }


    /**
     * delete expectation from database
     *
     * @param expectation
     */
    @Override
    public void deleteExpectation(Expectation expectation) {

        model.deleteExpectation(expectation);

        view.refreshExpectations(model.retrieveExpectations());
    }


    /**
     * start ExpectationPresentation
     *
     */
    @Override
    public void start() {

        setupRecyclerViewWithAdapter();
        retrieveAndDisplayExpectations();
    }
}
