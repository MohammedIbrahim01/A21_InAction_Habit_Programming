package com.rl.x.a21_inaction.habit;

import com.rl.x.a21_inaction.BasePresenter;

public interface HabitContract {

    interface Model {


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

        void saveNewTaskTemp();

        void goAddTask();

        void saveNewExpectationTemp();

        void goAddExpectation();

        void saveNewHabit();
    }
}
