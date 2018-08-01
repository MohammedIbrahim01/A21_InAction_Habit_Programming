package com.InAction.X.x21InAction.add_task.presenter;

import android.content.Context;

import com.InAction.X.x21InAction.add_task.AddTaskContract;
import com.InAction.X.x21InAction.add_task.model.AddTaskModel;
import com.InAction.X.x21InAction.add_task.model.TempTask;

public class AddTaskPresenter implements AddTaskContract.Presenter {

    private AddTaskModel model;
    private AddTaskContract.View view;


    public AddTaskPresenter(Context applicationContext, AddTaskContract.View view) {

        this.view = view;
        model = new AddTaskModel(applicationContext);
    }


    /**
     * get Task info from view then insert New TempTaskinto database
     */
    @Override
    public void insertTempTask() {

        model.insertTempTask(new TempTask(view.getNewTaskName(), view.getTaskCalendar(), view.getHabitName()));
        view.finishActivity();
    }
}
