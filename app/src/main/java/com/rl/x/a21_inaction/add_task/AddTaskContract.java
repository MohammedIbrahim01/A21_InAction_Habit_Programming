package com.rl.x.a21_inaction.add_task;

import com.rl.x.a21_inaction.add_task.model.TempTask;

import java.util.List;

public interface AddTaskContract {

    interface Model {

        void insertTempTask(TempTask tempTask);

        List<TempTask> getTempTaskList();
    }

    interface View {

        String getNewTaskName();

        void finishActivity();
    }

    interface Presenter {

        void insertTempTask();
    }
}
