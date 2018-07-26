package com.rl.x.a21_inaction.habit;

import com.rl.x.a21_inaction.BasePresenter;
import com.rl.x.a21_inaction.day_zero.model.Expectation;
import com.rl.x.a21_inaction.tasks.model.Task;

import java.util.List;

public interface HabitContract {

    interface Model {

        void saveNewHabit(String name, List<Task> taskListFromTemp, List<Expectation> expectationListFromTemp);
    }

    interface View {

        void finishActivity();

        String getHabitName();
    }

    interface Presenter extends BasePresenter {

        void goAddTask();

        void goAddExpectation();

        void saveNewHabit();
    }
}
