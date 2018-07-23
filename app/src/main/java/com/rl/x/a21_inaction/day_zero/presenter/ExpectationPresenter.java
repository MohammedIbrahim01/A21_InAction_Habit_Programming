package com.rl.x.a21_inaction.day_zero.presenter;

import android.content.Context;

import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.day_zero.ExpectationContract;
import com.rl.x.a21_inaction.day_zero.model.Expectation;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.List;

public class ExpectationPresenter implements ExpectationContract.Presenter {

    private Context applicationContext;
    private ExpectationContract.View view;

    public ExpectationPresenter(Context applicationContext, ExpectationContract.View view) {
        this.applicationContext = applicationContext;
        this.view = view;
    }

    @Override
    public void setupRecyclerViewWithAdapter() {
        view.setupRecyclerViewWithAdapter();
    }

    @Override
    public void retrieveAndDisplayExpectations() {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<Expectation> expectationList = AppDatabase.getInstance(applicationContext).expectationDao().getAllExpectations();
                view.displayExpectations(expectationList);
            }
        });
    }

    @Override
    public void insertExpectation(String name) {

        final Expectation newExpectation = new Expectation(name);

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(applicationContext).expectationDao().insertExpectation(newExpectation);
            }
        });
    }

    @Override
    public void insertMockExpectations() {

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(applicationContext).expectationDao().insertExpectation(new Expectation("Expectation #1"));
                AppDatabase.getInstance(applicationContext).expectationDao().insertExpectation(new Expectation("Expectation #2"));
                AppDatabase.getInstance(applicationContext).expectationDao().insertExpectation(new Expectation("Expectation #3"));
                AppDatabase.getInstance(applicationContext).expectationDao().insertExpectation(new Expectation("Expectation #4"));
            }
        });

    }

    @Override
    public void deleteExpectation(final Expectation expectation) {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                //delete expectation from app database
                AppDatabase.getInstance(applicationContext).expectationDao().deleteExpectation(expectation);

                //refresh expectations
                List<Expectation> expectationList = AppDatabase.getInstance(applicationContext).expectationDao().getAllExpectations();
                view.refreshExpectations(expectationList);
            }
        });
    }

    @Override
    public void start() {
        setupRecyclerViewWithAdapter();
        retrieveAndDisplayExpectations();
    }
}
