package com.rl.x.a21_inaction.habit;

import com.rl.x.a21_inaction.BasePresenter;
import com.rl.x.a21_inaction.day_zero.model.Expectation;
import com.rl.x.a21_inaction.habit.model.Habit;
import com.rl.x.a21_inaction.habit.model.TempExpectation;
import com.rl.x.a21_inaction.habit.model.TempTask;
import com.rl.x.a21_inaction.tasks.model.Task;

import java.util.List;

public interface HabitContract {

    interface Model {

        void saveNewTempTask(TempTask tempTask);

        void saveNewTempExpectation(TempExpectation tempExpectation);

        void saveNewHabit(Habit habit);

        List<TempTask> getAllTempTasks();

        List<TempExpectation> getAllTempExpectations();

        void saveTempTasksInRealTaskDatabase();

        void saveTempExpectationsInRealExpectationDatabase();

        void removeTempTasks();

        void removeTempExpectations();
    }

    interface AddTaskView {

        String getNewTaskName();

        void finishActivity();
    }

    interface AddExpectationView {

        String getNewExpectationName();

        void finishActivity();
    }

    interface NewHabitView {

        void goAddTask();

        void goAddExpectation();

        void finishActivity();
    }

    interface Presenter extends BasePresenter {

        void saveNewTempTask();

        void goAddTask();

        void saveNewTempExpectation();

        void goAddExpectation();

        void saveNewHabit();

        void removeTempTasksAndExpectations();
    }
}
