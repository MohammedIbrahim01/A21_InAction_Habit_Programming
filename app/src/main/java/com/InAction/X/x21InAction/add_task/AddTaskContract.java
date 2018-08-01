package com.InAction.X.x21InAction.add_task;

import com.InAction.X.x21InAction.add_task.model.TempTask;

import java.util.Calendar;
import java.util.List;

public interface AddTaskContract {

    interface Model {

        void insertTempTask(TempTask tempTask);

        List<TempTask> getTempTaskList();
    }

    interface View {

        String getNewTaskName();

        String getHabitName();

        void finishActivity();

        Calendar getTaskCalendar();
    }

    interface Presenter {

        void insertTempTask();
    }
}
