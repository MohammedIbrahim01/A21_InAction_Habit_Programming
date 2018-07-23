package com.rl.x.a21_inaction.tasks.Presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.tasks.TasksContract;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.model.TaskDao;
import com.rl.x.a21_inaction.tasks.model.TaskModel;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.List;
import java.util.concurrent.Executor;

public class TasksPresenter implements TasksContract.Presenter {


    private TasksContract.View view;

    private TaskModel model;


    public TasksPresenter(TasksContract.View view, Context applicationContext) {

        this.view = view;
        model = new TaskModel(applicationContext);
    }


    /**
     * setup recyclerView with adapter
     */
    @Override
    public void setupRecyclerViewWithAdapter() {

        view.setupRecyclerViewWithAdapter();
    }


    /**
     * retrieve tasks from application Database
     */
    @Override
    public void retrieveAndDisplayTasks() {

        view.displayTasks(model.retrieveTasks());
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

        view.refreshTasks(model.retrieveTasks());
    }


    /**
     * setup swipe task functionality
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
            }
        });

        return itemTouchHelper;
    }


    @Override
    public void start() {

        setupRecyclerViewWithAdapter();
        retrieveAndDisplayTasks();
        setupSwipeTaskFun();
    }
}
