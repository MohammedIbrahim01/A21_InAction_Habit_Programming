package com.InAction.X.x21InAction.tasks.model;

import android.content.Context;

import com.InAction.X.x21InAction.add_task.model.TempTaskDao;
import com.InAction.X.x21InAction.database.AppDatabase;
import com.InAction.X.x21InAction.tasks.TasksContract;
import com.InAction.X.x21InAction.utils.AppExecutors;

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

    @Override
    public void deleteAllTasks() {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                taskDao.deleteAllTasks();
            }
        });
    }
}
