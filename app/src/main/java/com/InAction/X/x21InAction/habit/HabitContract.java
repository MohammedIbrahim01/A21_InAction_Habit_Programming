package com.InAction.X.x21InAction.habit;

import com.InAction.X.x21InAction.habit.model.Habit;
import com.InAction.X.x21InAction.habit.view.CreateHabitAdapter;
import com.InAction.X.x21InAction.utils.CustomViewPager;

public interface HabitContract {

    interface Model {

        void insertHabit(Habit habit);

        Habit getHabit();
    }

    interface View{

        void setButtonsVisibilities(int page);

        CreateHabitAdapter getAdapter();

        CustomViewPager getViewPager();

        int getViewPagerCurrentItem();

        void setViewPagerCurrentItem(int page);
    }

    interface Presenter {

        void insertHabit(Habit habit);

        Habit getHabit();

        void setupAdapterWithViewPager();

        void nextPage();

        void previousPage();
    }

    interface Communication {

        void insertHabit(Habit habit);

        Habit getHabit();
    }
}
