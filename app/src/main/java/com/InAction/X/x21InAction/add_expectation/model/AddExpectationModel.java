package com.InAction.X.x21InAction.add_expectation.model;

import android.content.Context;

import com.InAction.X.x21InAction.add_expectation.AddExpectationContract;
import com.InAction.X.x21InAction.database.AppDatabase;
import com.InAction.X.x21InAction.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class AddExpectationModel implements AddExpectationContract.Model {

    private Executor diskIOExecutor;
    private TempExpectationDao tempExpectationDao;


    public AddExpectationModel(Context applicationContext) {

        diskIOExecutor = AppExecutors.getInstance().getDiskIO();
        tempExpectationDao = AppDatabase.getInstance(applicationContext).tempExpectationDao();
    }


    @Override
    public void insertTempExpectation(final TempExpectation tempExpectation) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempExpectationDao.insertTempExpectation(tempExpectation);
            }
        });
    }


    private List<TempExpectation> tempExpectationList = new ArrayList<>();
    private Boolean haveTempExpectationList;

    @Override
    public List<TempExpectation> getTempExpectationList() {

        haveTempExpectationList = false;

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempExpectationList = tempExpectationDao.getAllTempExpectations();
                haveTempExpectationList = true;
            }
        });

        while (!haveTempExpectationList) ;

        return tempExpectationList;
    }
}
