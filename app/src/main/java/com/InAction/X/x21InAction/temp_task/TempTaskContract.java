package com.InAction.X.x21InAction.temp_task;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.support.v7.widget.RecyclerView;

import com.InAction.X.x21InAction.temp_task.model.TempTask;
import com.InAction.X.x21InAction.temp_task.view.TempTasksAdapter;
import com.InAction.X.x21InAction.temp_task.view.TempTasksViewModel;

import java.util.Calendar;
import java.util.List;

public interface TempTaskContract {

    interface Model {

        void insertTempTask(TempTask tempTask);

        List<TempTask> getTempTaskList();

        LiveData<List<TempTask>> getTempTaskListLive();

        void deleteAllTempTasks();
    }

    interface View {

        interface AddTaskView{

            String getTaskName();

            String getHabitName();

            Calendar getCalendar();

            void finishActivity();
        }

        interface TempTasksView{

            TempTasksAdapter getAdapter();

            RecyclerView getRecyclerView();

            TempTasksViewModel getViewModel();

            LifecycleOwner getLifecycleOwner();
        }
    }

    interface Presenter {

        void insertTempTask();

        List<TempTask> getTempTaskList();

        LiveData<List<TempTask>> getTempTaskListLive();

        void deleteAllTempTasks();

        void setupRecyclerViewAndAdapter();

        void setupDisplayTempTasksLive();
    }

    interface Communication {

        List<TempTask> getTempTaskList();

        LiveData<List<TempTask>> getTempTaskListLive();

        void deleteAllTempTasks();
    }
}
