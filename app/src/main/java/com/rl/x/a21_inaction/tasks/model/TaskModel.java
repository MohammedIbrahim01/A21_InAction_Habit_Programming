package com.rl.x.a21_inaction.tasks.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.tasks.TasksContract;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TaskModel implements TasksContract.Model {

    private Executor diskIOExecutor;
    private TaskDao taskDao;

    public TaskModel(Context applicationContext) {
        diskIOExecutor = AppExecutors.getInstance().getDiskIO();
        taskDao = AppDatabase.getInstance(applicationContext).taskDao();
    }


    private List<Task> taskList;
    private boolean isFinish;


    @Override
    public List<Task> retrieveTasks() {

        isFinish = false;

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {
                taskList = taskDao.getAllTasks();
                isFinish = true;
            }
        });

        while (!isFinish);

        return taskList;
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
    public void insertMockTasks() {
        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    taskDao.insertTask(new Task("task #" + i, i));
                }
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

    public class AppCallable implements Callable<List<Task>> {

        @Override
        public List<Task> call() throws Exception {
            return taskDao.getAllTasks();
        }
    }
}
