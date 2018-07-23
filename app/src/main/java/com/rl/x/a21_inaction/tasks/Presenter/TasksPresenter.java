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
import java.util.concurrent.Executor;

public class TasksPresenter implements TasksContract.Presenter {


    private TasksContract.View view;
    private Context applicationContext;

    private Executor diskIO;
    private TaskDao taskDao;


    public TasksPresenter(TasksContract.View view, Context applicationContext) {
        this.view = view;
        this.applicationContext = applicationContext;

        diskIO = AppExecutors.getInstance().getDiskIO();
        taskDao = AppDatabase.getInstance(applicationContext).taskDao();
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

        diskIO.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    taskDao.insertTask(new Task("task #" + i));
                }
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

        diskIO.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.insertTask(newTask);
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

        diskIO.execute(new Runnable() {
            @Override
            public void run() {
                List<Task> taskList = taskDao.getAllTasks();
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
        diskIO.execute(new Runnable() {
            @Override
            public void run() {
                //delete task from database
                taskDao.deleteTask(task);

                //refresh tasks recyclerView
                view.refreshTasks(taskDao.getAllTasks());
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
