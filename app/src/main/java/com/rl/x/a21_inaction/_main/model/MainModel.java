package com.rl.x.a21_inaction._main.model;

import android.support.v4.app.FragmentManager;

import com.rl.x.a21_inaction._main.MainContract;
import com.rl.x.a21_inaction._main.view.AppFragmentPagerAdapter;
import com.rl.x.a21_inaction.achievements.view.AchievementsFragment;
import com.rl.x.a21_inaction.expectation.view.DayZeroFragment;
import com.rl.x.a21_inaction.tasks.view.TasksFragment;

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
        fragmentPagerAdapter.addFragment(new DayZeroFragment(), "day zero");

        return fragmentPagerAdapter;
    }
}
