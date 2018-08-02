package com.InAction.X.x21InAction.tasks;

import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.InAction.X.x21InAction.BasePresenter;
import com.InAction.X.x21InAction.tasks.model.Task;
import com.InAction.X.x21InAction.tasks.view.TasksAdapter;
import com.InAction.X.x21InAction.tasks.view.TasksViewModel;

import java.util.List;

public interface TasksContract {

    interface Model {

        void insertTaskList(List<Task> taskList);

        void deleteTask(Task task);

        void deleteAllTasks();

        List<Task> getAllTasks();
    }

    interface View {

        void setupSwipeTaskFunctionality(ItemTouchHelper itemTouchHelper);

        TasksAdapter getAdapter();

        RecyclerView getRecyclerView();

        void setTasks(List<Task> taskList);

        TasksViewModel getViewModel();

        LifecycleOwner getLifeCycleOwner();
    }

    interface Presenter {

        void setUpRecyclerViewWithAdapter();

        void setupTasksLive();

        ItemTouchHelper getItemTouchHelper();

        void setupSwipeTaskFunctionality();

        void deleteTask(Task task);

        void deleteAllTasks();

        void insertTaskList(List<Task> taskList);

        List<Task> getAllTasks();
    }

    interface Communication {

        void deleteTask(Task task);

        void deleteAllTasks();

        void insertTaskList(List<Task> taskList);

        List<Task> getAllTasks();
    }
}
