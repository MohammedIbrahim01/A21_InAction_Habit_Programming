package com.InAction.X.x21InAction.temp_task.communication;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.InAction.X.x21InAction.temp_task.TempTaskContract;
import com.InAction.X.x21InAction.temp_task.model.TempTask;
import com.InAction.X.x21InAction.temp_task.presenter.TempTaskPresenter;

import java.util.List;

public class TempTaskCommunication implements TempTaskContract.Communication {


    private TempTaskPresenter presenter;


    public TempTaskCommunication(Context applicationContext) {

        presenter = new TempTaskPresenter(applicationContext);
    }


    @Override
    public List<TempTask> getTempTaskList() {

        return presenter.getTempTaskList();
    }

    @Override
    public LiveData<List<TempTask>> getTempTaskListLive() {

        return presenter.getTempTaskListLive();
    }

    @Override
    public void deleteAllTempTasks() {

        presenter.deleteAllTempTasks();
    }
}
