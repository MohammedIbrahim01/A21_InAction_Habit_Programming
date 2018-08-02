package com.InAction.X.x21InAction.habit.view;

import android.support.v4.app.Fragment;

import com.InAction.X.x21InAction.temp_expectation.view.TempExpectationsFragment;
import com.InAction.X.x21InAction.temp_task.view.TempTasksFragment;

public class CreateHabitScreens {

    public static Fragment getScreen(int page) {

        switch (page) {

            case 1:
                return new HabitNameFragment();

            case 3:
                return new TempExpectationsFragment();

            case 5:
                return new TempTasksFragment();

            case 6:
                return new HabitOverviewScreen();

            default:
                return CreateHabitDisplayScreens.getScreen(page);
        }
    }
}
