package com.InAction.X.x21InAction.temp_expectation.model;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.InAction.X.x21InAction.temp_expectation.TempExpectationContract;
import com.InAction.X.x21InAction.database.AppDatabase;
import com.InAction.X.x21InAction.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class TempExpectationModel implements TempExpectationContract.Model {


    private Executor diskIOExecutor;
    private TempExpectationDao tempExpectationDao;


    public TempExpectationModel(Context applicationContext) {

        diskIOExecutor = AppExecutors.getInstance().getDiskIO();
        tempExpectationDao = AppDatabase.getInstance(applicationContext).tempExpectationDao();
    }


    /**
     * insert TempExpectation
     *
     * @param tempExpectation
     */
    @Override
    public void insertTempExpectation(final TempExpectation tempExpectation) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempExpectationDao.insertTempExpectation(tempExpectation);
            }
        });
    }


    /**
     * get TempExpectation List
     */
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


    /**
     * get TempExpectation List Live
     *
     * @return
     */
    private LiveData<List<TempExpectation>> tempExpectationListLive;
    private Boolean haveTempExpectationListLive;


    @Override
    public LiveData<List<TempExpectation>> getTempExpectationListLive() {

        haveTempExpectationListLive = false;

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempExpectationListLive = tempExpectationDao.getAllTempExpectationsLive();

                haveTempExpectationListLive = true;
            }
        });


        while (!haveTempExpectationListLive) ;


        return tempExpectationListLive;
    }


    /**
     * delete all TempExpectations
     */
    @Override
    public void deleteAllTempExpectations() {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempExpectationDao.deleteAllTempExpectations();
            }
        });
    }

}
