package com.rl.x.a21_inaction._main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.rl.x.a21_inaction._main.view.AppFragmentPagerAdapter;

public interface MainContract {

    interface View{

        void setupTabLayoutAndViewPager(AppFragmentPagerAdapter adapter);

        FragmentManager getAppSupportFragmentManager();

        void goAddHabit();
    }

    interface Presenter{

        void setupTabLayoutAndViewPager();

        void goAddHabit();

        void displayDayTasks();
    }
}
