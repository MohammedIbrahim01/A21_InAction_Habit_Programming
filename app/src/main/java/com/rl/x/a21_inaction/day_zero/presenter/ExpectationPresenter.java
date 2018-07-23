package com.rl.x.a21_inaction.day_zero.presenter;

import android.content.Context;

import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.day_zero.ExpectationContract;
import com.rl.x.a21_inaction.day_zero.model.Expectation;
import com.rl.x.a21_inaction.day_zero.model.ExpectationDao;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.List;
import java.util.concurrent.Executor;

public class ExpectationPresenter implements ExpectationContract.Presenter {

    private Context applicationContext;
    private ExpectationContract.View view;

    private Executor diskIO;
    private ExpectationDao expectationDao;

    public ExpectationPresenter(Context applicationContext, ExpectationContract.View view) {
        this.applicationContext = applicationContext;
        this.view = view;

        diskIO = AppExecutors.getInstance().getDiskIO();
        expectationDao = AppDatabase.getInstance(applicationContext).expectationDao();
    }

    @Override
    public void setupRecyclerViewWithAdapter() {
        view.setupRecyclerViewWithAdapter();
    }

    @Override
    public void retrieveAndDisplayExpectations() {
        diskIO.execute(new Runnable() {
            @Override
            public void run() {
                List<Expectation> expectationList = expectationDao.getAllExpectations();
                view.displayExpectations(expectationList);
            }
        });
    }

    @Override
    public void insertExpectation(String name) {

        final Expectation newExpectation = new Expectation(name);

        diskIO.execute(new Runnable() {
            @Override
            public void run() {
                expectationDao.insertExpectation(newExpectation);
            }
        });
    }

    @Override
    public void insertMockExpectations() {
         diskIO.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    expectationDao.insertExpectation(new Expectation("Expectation #" + i));
                }
            }
        });

    }

    @Override
    public void deleteExpectation(final Expectation expectation) {
        diskIO.execute(new Runnable() {
            @Override
            public void run() {
                //delete expectation from app database
                expectationDao.deleteExpectation(expectation);

                //refresh expectations
                List<Expectation> expectationList = expectationDao.getAllExpectations();
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
