package com.rl.x.a21_inaction.tasks.Presenter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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

    private Fragment fragment;
    private TaskModel model;
    private TasksContract.View view;
    private TasksViewModel viewModel;
    private AppManager manager;


    public TasksPresenter(Fragment fragment,TasksContract.View view) {

        this.fragment = fragment;
        model = new TaskModel(fragment.getContext().getApplicationContext());
        this.view = view;
        viewModel = ViewModelProviders.of(fragment.getActivity()).get(TasksViewModel.class);
        manager = new AppManager(fragment.getContext().getApplicationContext());
    }


    /**
     * setup recyclerView with adapter
     */
    @Override
    public void attachRecyclerViewWithAdapter() {

        view.attachRecyclerViewWithAdapter();
    }


    /**
     * setup tasks Live
     */
    @Override
    public void setupTasksLive() {

        viewModel.getTasks().observe(fragment.getActivity(), new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {

                view.setTasks(tasks);
            }
        });
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
     */
    @Override
    public void setupSwipeTaskFunctionality() {

        view.setupSwipeTaskFunctionality(getItemTouchHelper());
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
                manager.addAchievementFromTask(swipedTask);
            }
        });

        return itemTouchHelper;
    }


    @Override
    public void start() {

        attachRecyclerViewWithAdapter();
        setupTasksLive();
        setupSwipeTaskFunctionality();
    }
}
