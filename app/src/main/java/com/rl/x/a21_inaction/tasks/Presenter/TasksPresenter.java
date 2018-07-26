package com.rl.x.a21_inaction.tasks.Presenter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.rl.x.a21_inaction.manager.AppManager;
import com.rl.x.a21_inaction.tasks.TasksContract;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.model.TaskModel;
import com.rl.x.a21_inaction.tasks.view.TasksViewModel;

import java.util.List;

public class TasksPresenter implements TasksContract.Presenter {


    private TasksContract.View view;
    private AppManager manager;
    private TaskModel model;
    private TasksViewModel viewModel;
    private Fragment fragment;


    public TasksPresenter(TasksContract.View view, Fragment fragment) {

        this.view = view;
        manager = new AppManager(fragment.getContext().getApplicationContext());
        model = new TaskModel(fragment.getContext().getApplicationContext());
        viewModel = ViewModelProviders.of(fragment.getActivity()).get(TasksViewModel.class);
        this.fragment = fragment;
    }


    /**
     * setup recyclerView with adapter
     */
    @Override
    public void setupRecyclerViewWithAdapter() {

        view.setupRecyclerViewWithAdapter();
    }


    /**
     * setup tasks Live
     *
     */
    @Override
    public void setupTasksLive() {

        viewModel.getTasks().observe(fragment.getActivity(), new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                view.refreshTasks(tasks);
            }
        });
    }


    /**
     * insert task into database
     *
     * @param name
     */
    @Override
    public void insertTaskIntoDatabase(String name) {

        model.insertTask(new Task(name));
    }


    /**
     * insert mock tasks into database for testing
     *
     */
    @Override
    public void insertMockTasksIntoDatabase() {

        model.insertMockTasks();
    }


    /**
     * delete task from application database then refresh tasks
     *
     * @param task
     */
    @Override
    public void deleteTask(Task task) {

        model.deleteTask(task);
    }


    /**
     * setup swipe task functionality
     *
     */
    @Override
    public void setupSwipeTaskFun() {

        view.setupSwipeTaskFun(getItemTouchHelper());
    }


    /**
     * get an instance of ItemTouchHelper
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
                //when task was swiped : get that Task then delete it
                Task swipedTask = view.getAdapter().getTaskList().get(viewHolder.getAdapterPosition());
                deleteTask(swipedTask);
                manager.addAchievement(swipedTask);
            }
        });

        return itemTouchHelper;
    }


    @Override
    public void start() {

        setupRecyclerViewWithAdapter();
        setupTasksLive();
        setupSwipeTaskFun();
    }
}
