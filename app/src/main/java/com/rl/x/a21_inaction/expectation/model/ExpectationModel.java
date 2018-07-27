package com.rl.x.a21_inaction.expectation.model;

import android.content.Context;

import com.rl.x.a21_inaction.add_expectation.model.TempExpectationDao;
import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.expectation.ExpectationContract;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.List;
import java.util.concurrent.Executor;

public class ExpectationModel implements ExpectationContract.Model {

    private Executor diskIOExecutor;
    private ExpectationDao expectationDao;


    public ExpectationModel(Context applicationContext) {

        diskIOExecutor = AppExecutors.getInstance().getDiskIO();
        expectationDao = AppDatabase.getInstance(applicationContext).expectationDao();
    }


    /**
     * insert Expectation to database
     *
     * @param expectation
     */
    @Override
    public void insertExpectation(final Expectation expectation) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                expectationDao.insertExpectation(expectation);
            }
        });
    }


    /**
     * delete Expectation from database
     *
     * @param expectation
     */
    @Override
    public void deleteExpectation(final Expectation expectation) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                expectationDao.deleteExpectation(expectation);
            }
        });
    }


    @Override
    public void insertExpectationList(final List<Expectation> expectationListFromHabit) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                expectationDao.insertAllExpectation(expectationListFromHabit);
            }
        });
    }
}
