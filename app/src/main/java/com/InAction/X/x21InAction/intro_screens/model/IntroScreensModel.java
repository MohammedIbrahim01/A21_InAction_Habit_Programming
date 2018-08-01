package com.InAction.X.x21InAction.intro_screens.model;

import android.support.v4.app.Fragment;

import com.InAction.X.x21InAction.temp_expectation.view.TempExpectationsFragment;
import com.InAction.X.x21InAction.intro_screens.IntroHabitOverviewScreen;
import com.InAction.X.x21InAction.intro_screens.IntroTasksScreen;

public class IntroScreensModel {

    public static Fragment getScreen(int page) {

        switch (page) {

            case 7:
                return new TempExpectationsFragment();
            case 9:
                return new IntroTasksScreen();
            case 10:
                return new IntroHabitOverviewScreen();
            default:
                return IntroFragments.getIntro(page);
        }
    }
}
