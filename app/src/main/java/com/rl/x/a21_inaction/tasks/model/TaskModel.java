package com.rl.x.a21_inaction.tasks.model;

import android.content.Context;

import com.rl.x.a21_inaction.add_task.model.TempTaskDao;
import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.tasks.TasksContract;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executor;

public class TaskModel implements TasksContract.Model {

    private Executor diskIOExecutor;
    private TaskDao taskDao;

    public TaskModel(Context applicationContext) {

        diskIOExecutor = AppExecutors.getInstance().getDiskIO();
        taskDao = AppDatabase.getInstance(applicationContext).taskDao();
    }


    @Override
    public void insertTask(final Task task) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                taskDao.insertTask(task);
            }
        });
    }


    @Override
    public void insertTaskList(final List<Task> taskList) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                taskDao.insertAllTask(taskList);
            }
        });
    }


    @Override
    public void deleteTask(final Task task) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                taskDao.deleteTask(task);
            }
        });
    }
}
