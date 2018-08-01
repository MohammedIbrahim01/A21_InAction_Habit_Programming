package com.InAction.X.x21InAction.temp_task.model;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.InAction.X.x21InAction.temp_task.TempTaskContract;
import com.InAction.X.x21InAction.database.AppDatabase;
import com.InAction.X.x21InAction.utils.AppExecutors;

import java.util.List;
import java.util.concurrent.Executor;

public class TempTaskModel implements TempTaskContract.Model {


    private Executor diskIOExecutor;
    private TempTaskDao tempTaskDao;


    public TempTaskModel(Context applicationContext) {

        diskIOExecutor = AppExecutors.getInstance().getDiskIO();
        tempTaskDao = AppDatabase.getInstance(applicationContext).tempTaskDao();
    }


    /**
     * insert TempTask
     *
     * @param tempTask
     */
    @Override
    public void insertTempTask(final TempTask tempTask) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempTaskDao.insertTempTask(tempTask);
            }
        });
    }


    /**
     * get TempTask List
     */
    private List<TempTask> tempTaskList;
    private Boolean haveTempTaskList;

    @Override
    public List<TempTask> getTempTaskList() {

        haveTempTaskList = false;

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempTaskList = tempTaskDao.getAllTempTasks();
                haveTempTaskList = true;
            }
        });

        while (!haveTempTaskList) ;

        return tempTaskList;
    }


    /**
     * get TempTAsk List Live
     */
    private LiveData<List<TempTask>> tempTaskListLive;
    private Boolean haveTempTaskListLive;

    @Override
    public LiveData<List<TempTask>> getTempTaskListLive() {

        haveTempTaskListLive = false;

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempTaskListLive = tempTaskDao.getAllTempTasksLive();

                haveTempTaskListLive = true;
            }
        });

        while (!haveTempTaskListLive) ;

        return tempTaskListLive;
    }


    /**
     * delete all TempTasks
     */
    @Override
    public void deleteAllTempTasks() {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempTaskDao.deleteAllTempTasks();
            }
        });
    }
}
