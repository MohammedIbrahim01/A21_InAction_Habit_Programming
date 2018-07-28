package com.rl.x.a21_inaction.tasks.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.tasks.model.Task;

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
