package com.rl.x.a21_inaction._main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.rl.x.a21_inaction.BasePresenter;
import com.rl.x.a21_inaction._main.view.AppFragmentPagerAdapter;

public interface MainContract {

    interface Model {

        AppFragmentPagerAdapter getFragmentPagerAdapter(FragmentManager appSupportFragmentManager);
    }

    interface View {

        void setupTabLayoutAndViewPager(AppFragmentPagerAdapter fragmentPagerAdapter);

        void displayCounter(String count);

        FragmentManager getAppSupportFragmentManager();
    }

    interface Presenter {

        void setupTabLayoutAndViewPager();

        void goAddHabit();

        void clearDatabase();

        void stopTime();

        void setCounter();
    }
}
