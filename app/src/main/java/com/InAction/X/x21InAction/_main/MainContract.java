package com.InAction.X.x21InAction._main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.InAction.X.x21InAction.BasePresenter;
import com.InAction.X.x21InAction._main.view.AppFragmentPagerAdapter;

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
