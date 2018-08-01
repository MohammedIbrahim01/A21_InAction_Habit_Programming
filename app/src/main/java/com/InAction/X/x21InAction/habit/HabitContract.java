package com.InAction.X.x21InAction.habit;

import com.InAction.X.x21InAction.BasePresenter;
import com.InAction.X.x21InAction.expectation.model.Expectation;
import com.InAction.X.x21InAction.tasks.model.Task;

import java.util.List;

public interface HabitContract {

    interface Model {

        void saveNewHabit(String name, List<Task> taskListFromTemp, List<Expectation> expectationListFromTemp);

        List<Task> getTasksFromHabit();

        List<Expectation> getExpectationsFromHabit();
    }

    interface View {

        void finishActivity();

        String getHabitName();
    }

    interface Presenter extends BasePresenter {

        void goAddTask(String habitName);

        void goAddExpectation(String habitName);

        void saveNewHabit();
    }
}
