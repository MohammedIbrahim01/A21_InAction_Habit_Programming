package com.InAction.X.x21InAction.add_task.model;

import android.content.Context;

import com.InAction.X.x21InAction.add_task.AddTaskContract;
import com.InAction.X.x21InAction.database.AppDatabase;
import com.InAction.X.x21InAction.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class AddTaskModel implements AddTaskContract.Model {

    private Executor diskIOExecutor;
    private TempTaskDao tempTaskDao;


    public AddTaskModel(Context applicationContext) {

        diskIOExecutor = AppExecutors.getInstance().getDiskIO();
        tempTaskDao = AppDatabase.getInstance(applicationContext).tempTaskDao();
    }


    @Override
    public void insertTempTask(final TempTask tempTask) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempTaskDao.insertTempTask(tempTask);
            }
        });
    }


    private List<TempTask> tempTaskList = new ArrayList<>();
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
}
