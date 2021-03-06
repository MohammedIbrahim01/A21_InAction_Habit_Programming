package com.InAction.X.x21InAction.tasks.communication;

import android.content.Context;

import com.InAction.X.x21InAction.tasks.Presenter.TasksPresenter;
import com.InAction.X.x21InAction.tasks.TasksContract;
import com.InAction.X.x21InAction.tasks.model.Task;

import java.util.List;

public class TaskCommunication implements TasksContract.Communication {


    private TasksPresenter presenter;


    public TaskCommunication(Context applicationContext) {

        presenter = new TasksPresenter(applicationContext);
    }


    @Override
    public void deleteTask(Task task) {

        presenter.deleteTask(task);
    }


    @Override
    public void deleteAllTasks() {

        presenter.deleteAllTasks();
    }


    @Override
    public void insertTaskList(List<Task> taskList) {

        presenter.insertTaskList(taskList);
    }


    @Override
    public List<Task> getAllTasks() {

        return presenter.getAllTasks();
    }
}
