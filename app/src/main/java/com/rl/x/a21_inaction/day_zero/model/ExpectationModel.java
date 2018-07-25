package com.rl.x.a21_inaction.day_zero.model;

import android.content.Context;

import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.day_zero.ExpectationContract;
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
     * insert mock Expectations to database for testing
     *
     */
    @Override
    public void insertMockExpectation() {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 4; i++) {

                    expectationDao.insertExpectation(new Expectation("expectation #" + i));

                }
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
}
