package com.rl.x.a21_inaction.add_task;

import com.rl.x.a21_inaction.add_task.model.TempTask;

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
