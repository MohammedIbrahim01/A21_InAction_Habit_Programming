package com.InAction.X.x21InAction.tasks.Presenter;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.InAction.X.x21InAction.manager.AppManager;
import com.InAction.X.x21InAction.tasks.TasksContract;
import com.InAction.X.x21InAction.tasks.model.Task;
import com.InAction.X.x21InAction.tasks.model.TaskModel;
import com.InAction.X.x21InAction.tasks.view.TasksAdapter;
import com.InAction.X.x21InAction.tasks.view.TasksViewModel;

import java.util.List;

public class TasksPresenter implements TasksContract.Presenter {


    private TaskModel model;
    private TasksContract.View view;
    private TasksViewModel viewModel;
    private Context applicationContext;


    public TasksPresenter(Context applicationContext) {

        this.applicationContext = applicationContext;
        model = new TaskModel(applicationContext);
    }


    public TasksPresenter(Context applicationContext, TasksContract.View view) {

        this.view = view;
        model = new TaskModel(applicationContext);
        this.applicationContext = applicationContext;
    }


    /**
     * setup recyclerView with adapter
     */
    @Override
    public void setUpRecyclerViewWithAdapter() {

        TasksAdapter adapter = view.getAdapter();
        RecyclerView recyclerView = view.getRecyclerView();

        recyclerView.setLayoutManager(new LinearLayoutManager(applicationContext));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


    /**
     * setup tasks Live
     */
    @Override
    public void setupTasksLive() {

        viewModel = view.getViewModel();

        viewModel.getTaskList().observe(view.getLifeCycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {

                view.setTasks(tasks);
            }
        });
    }


    /**
     * delete task (Model)
     *
     * @param task
     */
    @Override
    public void deleteTask(Task task) {

        model.deleteTask(task);
    }


    /**
     * delete all Tasks (Model)
     */
    @Override
    public void deleteAllTasks() {

        model.deleteAllTasks();
    }


    /**
     * insert Task List(Model)
     *
     * @param taskList
     */
    @Override
    public void insertTaskList(List<Task> taskList) {

        model.insertTaskList(taskList);
    }

    /**
     * getScreen all Tasks (Model)
     */
    @Override
    public List<Task> getAllTasks() {

        return model.getAllTasks();
    }


    /**
     * setup swipe task functionality
     */
    @Override
    public void setupSwipeTaskFunctionality() {

        view.setupSwipeTaskFunctionality(getItemTouchHelper());
    }


    /**
     * getScreen an instance of ItemTouchHelper
     *
     * @return ItemTouchHelper
     */
    @Override
    public ItemTouchHelper getItemTouchHelper() {

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                //when task was swiped : getScreen that Task then delete it
                Task swipedTask = view.getAdapter().getTaskList().get(viewHolder.getAdapterPosition());
                deleteTask(swipedTask);
                AppManager.addAchievementFromTask(applicationContext, swipedTask);
            }
        });

        return itemTouchHelper;
    }
}
