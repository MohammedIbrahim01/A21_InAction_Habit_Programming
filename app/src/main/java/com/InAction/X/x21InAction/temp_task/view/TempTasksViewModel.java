package com.InAction.X.x21InAction.temp_task.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.InAction.X.x21InAction.temp_task.model.TempTask;
import com.InAction.X.x21InAction.database.AppDatabase;

import java.util.List;

public class TempTasksViewModel extends AndroidViewModel {


    private LiveData<List<TempTask>> tempTasksList;


    public TempTasksViewModel(@NonNull Application application) {
        super(application);

        tempTasksList = AppDatabase.getInstance(application.getApplicationContext()).tempTaskDao().getAllTempTasksLive();
    }


    public LiveData<List<TempTask>> getTempTasksList() {

        return tempTasksList;
    }
}
