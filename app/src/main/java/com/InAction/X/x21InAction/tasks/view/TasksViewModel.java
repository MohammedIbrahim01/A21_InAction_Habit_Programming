package com.InAction.X.x21InAction.tasks.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.InAction.X.x21InAction.database.AppDatabase;
import com.InAction.X.x21InAction.tasks.model.Task;

import java.util.List;

public class TasksViewModel extends AndroidViewModel {

    private LiveData<List<Task>> tasks;

    public TasksViewModel(@NonNull Application application) {
        super(application);
        tasks = AppDatabase.getInstance(application.getApplicationContext()).taskDao().getAllTasksLive();
    }

    public LiveData<List<Task>> getTasks() {
        return tasks;
    }
}
