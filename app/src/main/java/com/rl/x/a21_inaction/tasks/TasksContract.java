package com.rl.x.a21_inaction.tasks;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.rl.x.a21_inaction.BasePresenter;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.view.TasksAdapter;

import java.util.List;

public interface TasksContract {

    interface Model {

        List<Task> retrieveTasks();

        void insertTask(Task task);

        void insertMockTasks();

        void deleteTask(Task task);
    }

    interface View {

        void displayTasks(List<Task> taskList);

        void setupSwipeTaskFun(ItemTouchHelper itemTouchHelper);

        TasksAdapter getAdapter();

        RecyclerView getTasksRecyclerView();

        void refreshTasks(List<Task> taskList);

        void setupRecyclerViewWithAdapter();
    }

    interface Presenter extends BasePresenter {

        void setupRecyclerViewWithAdapter();

        void retrieveAndDisplayTasks();

        ItemTouchHelper getItemTouchHelper();

        void setupSwipeTaskFun();

        void insertTaskIntoDatabase(String name);

        void insertMockTasksIntoDatabase();

        void deleteTask(Task task);

    }
}
