package com.rl.x.a21_inaction.add_task.presenter;

import android.content.Context;

import com.rl.x.a21_inaction.add_task.AddTaskContract;
import com.rl.x.a21_inaction.add_task.model.AddTaskModel;
import com.rl.x.a21_inaction.add_task.model.TempTask;

public class AddTaskPresenter implements AddTaskContract.Presenter {

    private AddTaskContract.View view;
    private AddTaskModel model;

    public AddTaskPresenter(Context applicationContext, AddTaskContract.View view) {

        this.view = view;
        model = new AddTaskModel(applicationContext);
    }

    @Override
    public void insertTempTask() {

        model.insertTempTask(new TempTask(view.getNewTaskName()));
        view.finishActivity();
    }
}
