package com.InAction.X.x21InAction.temp_task.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.InAction.X.x21InAction.temp_task.TempTaskContract;
import com.InAction.X.x21InAction.temp_task.model.TempTaskModel;
import com.InAction.X.x21InAction.temp_task.model.TempTask;
import com.InAction.X.x21InAction.temp_task.view.TempTasksAdapter;
import com.InAction.X.x21InAction.temp_task.view.TempTasksViewModel;

import java.util.List;

public class TempTaskPresenter implements TempTaskContract.Presenter {


    private TempTaskModel model;
    private Context applicationContext;
    private TempTaskContract.View.AddTaskView addTaskView;
    private TempTaskContract.View.TempTasksView tempTasksView;


    /**
     * first constructor to use in TempTaskCommunication
     *
     * @param applicationContext
     */
    public TempTaskPresenter(Context applicationContext) {

        this.applicationContext = applicationContext;
        model = new TempTaskModel(applicationContext);
    }


    /**
     * second constructor to use in AddTaskActivity
     *
     * @param applicationContext
     * @param addTaskView
     */
    public TempTaskPresenter(Context applicationContext, TempTaskContract.View.AddTaskView addTaskView) {

        this.addTaskView = addTaskView;
        this.applicationContext = applicationContext;
        model = new TempTaskModel(applicationContext);
    }


    /**
     * third constructor to use in TempTasksFragment
     *
     * @param applicationContext
     * @param tempTasksView
     */
    public TempTaskPresenter(Context applicationContext, TempTaskContract.View.TempTasksView tempTasksView) {

        this.applicationContext = applicationContext;
        this.tempTasksView = tempTasksView;
        model = new TempTaskModel(applicationContext);
    }


    /**
     * insert TempTask (Model)
     * <p>
     * then finish the pop up AddTaskActivity
     */
    @Override
    public void insertTempTask() {

        model.insertTempTask(new TempTask(addTaskView.getTaskName(), addTaskView.getCalendar(), addTaskView.getHabitName()));
        addTaskView.finishActivity();
    }


    /**
     * get TempTask List (Model)
     *
     * @return
     */
    @Override
    public List<TempTask> getTempTaskList() {

        return model.getTempTaskList();
    }


    /**
     * get TempTask List Live (Model)
     *
     * @return
     */
    @Override
    public LiveData<List<TempTask>> getTempTaskListLive() {

        return model.getTempTaskListLive();
    }


    /**
     * delete all TempTasks (Model)
     */
    @Override
    public void deleteAllTempTasks() {

        model.deleteAllTempTasks();
    }


    /**
     * setup recyclerView and adapter from tempTasksFragment
     */
    @Override
    public void setupRecyclerViewAndAdapter() {

        TempTasksAdapter adapter = tempTasksView.getAdapter();
        RecyclerView recyclerView = tempTasksView.getRecyclerView();

        recyclerView.setLayoutManager(new LinearLayoutManager(applicationContext));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


    /**
     * get TempTasks List Live from tempTasksViewModel
     * <p>
     * and set adapter's tempTaskList to its value
     */
    @Override
    public void setupDisplayTempTasksLive() {

        TempTasksViewModel viewModel = tempTasksView.getViewModel();
        final TempTasksAdapter adapter = tempTasksView.getAdapter();


        viewModel.getTempTasksList().observe(tempTasksView.getLifecycleOwner(), new Observer<List<TempTask>>() {
            @Override
            public void onChanged(@Nullable List<TempTask> tempTasks) {

                adapter.setTempTaskList(tempTasks);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
