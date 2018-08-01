package com.InAction.X.x21InAction.habit;

import com.InAction.X.x21InAction.habit.model.Habit;

public interface HabitContract {

    interface Model {

        void insertHabit(Habit habit);

        Habit getHabit();
    }

    interface Presenter {

        void insertHabit(Habit habit);
    }

    interface Communication {

        void insertHabit(Habit habit);
    }
}
