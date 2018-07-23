package com.rl.x.a21_inaction.tasks;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.rl.x.a21_inaction.BasePresenter;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.view.TasksAdapter;

import java.util.List;

public interface TasksContract {

    interface ViewInterface {

        void displayTasks(List<Task> taskList);

        void setupSwipeTaskFun(ItemTouchHelper itemTouchHelper);

        TasksAdapter getAdapter();

        RecyclerView getTasksRecyclerView();

        void refreshTasksRecyclerView(List<Task> taskList);

        void setupRecyclerViewWithAdapter();
    }

    interface PresenterInterface extends BasePresenter{

        void setupRecyclerViewWithAdapter();

        void retrieveAndDisplayTasks();

        ItemTouchHelper getItemTouchHelper();

        void setupSwipeTaskFun();

        void insertTaskIntoDatabase(String name);

        void insertMockTasksIntoDatabase();

        void deleteTask(Task task);

    }
}
