package com.rl.x.a21_inaction.habit;

import com.rl.x.a21_inaction.BasePresenter;
import com.rl.x.a21_inaction.expectation.model.Expectation;
import com.rl.x.a21_inaction.tasks.model.Task;

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

        void goAddExpectation();

        void saveNewHabit();
    }
}
