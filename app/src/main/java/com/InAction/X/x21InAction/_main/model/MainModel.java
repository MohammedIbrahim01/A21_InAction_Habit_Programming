package com.InAction.X.x21InAction._main.model;

import android.support.v4.app.FragmentManager;

import com.InAction.X.x21InAction._main.MainContract;
import com.InAction.X.x21InAction._main.view.AppFragmentPagerAdapter;
import com.InAction.X.x21InAction.achievements.view.AchievementsFragment;
import com.InAction.X.x21InAction.expectation.view.ExpectationFragment;
import com.InAction.X.x21InAction.tasks.view.TasksFragment;

public class MainModel implements MainContract.Model {

    private AppFragmentPagerAdapter fragmentPagerAdapter;

    public MainModel() {
        //empty constructor
    }

    /**
     * fill fragmentPagerAdapter with three fragment then return it
     *
     * @param supportFragmentManager
     * @return
     */
    @Override
    public AppFragmentPagerAdapter getFragmentPagerAdapter(FragmentManager supportFragmentManager) {

        AppFragmentPagerAdapter fragmentPagerAdapter = new AppFragmentPagerAdapter(supportFragmentManager);
        fragmentPagerAdapter.addFragment(new TasksFragment(), "tasks");
        fragmentPagerAdapter.addFragment(new AchievementsFragment(), "achievements");
        fragmentPagerAdapter.addFragment(new ExpectationFragment(), "day zero");

        return fragmentPagerAdapter;
    }
}
