package com.rl.x.a21_inaction.habit;

import com.rl.x.a21_inaction.BasePresenter;

public interface HabitContract {

    interface Model {

        void saveNewHabit(String name);
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
