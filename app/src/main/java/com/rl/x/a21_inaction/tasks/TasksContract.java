package com.rl.x.a21_inaction.tasks;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.rl.x.a21_inaction.BasePresenter;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.view.TasksAdapter;

import java.util.List;

public interface TasksContract {

    interface Model {

        void insertTaskList(List<Task> taskList);

        void deleteTask(Task task);


        void deleteAllTasks();
    }

    interface View {

        void setupSwipeTaskFunctionality(ItemTouchHelper itemTouchHelper);

        TasksAdapter getAdapter();

        void setTasks(List<Task> taskList);

        void attachRecyclerViewWithAdapter();
    }

    interface Presenter extends BasePresenter {

        void attachRecyclerViewWithAdapter();

        void setupTasksLive();

        ItemTouchHelper getItemTouchHelper();

        void setupSwipeTaskFunctionality();

        void deleteTask(Task task);
    }
}
