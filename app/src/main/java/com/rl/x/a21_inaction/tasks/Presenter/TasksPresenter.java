package com.rl.x.a21_inaction.tasks.Presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.tasks.TasksContract;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.model.TaskDao;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.List;

public class TasksPresenter implements TasksContract.Presenter {


    private TasksContract.View view;
    private Context applicationContext;


    public TasksPresenter(TasksContract.View view, Context applicationContext) {
        this.view = view;
        this.applicationContext = applicationContext;
    }


    /**
     * setup recyclerView with adapter
     */
    @Override
    public void setupRecyclerViewWithAdapter() {
        view.setupRecyclerViewWithAdapter();
    }


    /**
     * insert mock tasks into database
     */
    @Override
    public void insertMockTasksIntoDatabase() {

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(applicationContext).taskDao()
                        .insertTask(new Task("task1"));
                AppDatabase.getInstance(applicationContext).taskDao()
                        .insertTask(new Task("task2"));
                AppDatabase.getInstance(applicationContext).taskDao()
                        .insertTask(new Task("task3"));
                AppDatabase.getInstance(applicationContext).taskDao()
                        .insertTask(new Task("task4"));
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
        final Task newTask = new Task(name);

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(applicationContext).taskDao()
                        .insertTask(newTask);
            }
        });
    }


    /**
     * retrieve tasks from application Database
     *
     * @return List of tasks
     */
    @Override
    public void retrieveAndDisplayTasks() {

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<Task> taskList = AppDatabase.getInstance(applicationContext).taskDao()
                        .getAllTasks();
                view.displayTasks(taskList);
            }
        });

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


    /**
     * delete task from application database then refresh tasks recyclerView
     *
     * @param task
     */
    @Override
    public void deleteTask(final Task task) {
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                TaskDao taskDao = AppDatabase.getInstance(applicationContext).taskDao();

                //delete task from database
                taskDao.deleteTask(task);

                //refresh tasks recyclerView
                List<Task> taskList = taskDao.getAllTasks();
                view.refreshTasksRecyclerView(taskList);
            }
        });
    }


    @Override
    public void start() {
        setupRecyclerViewWithAdapter();
        retrieveAndDisplayTasks();
        setupSwipeTaskFun();

    }
}
