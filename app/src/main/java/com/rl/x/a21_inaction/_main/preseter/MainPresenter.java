package com.rl.x.a21_inaction._main.preseter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.rl.x.a21_inaction._main.MainContract;
import com.rl.x.a21_inaction._main.model.MainModel;
import com.rl.x.a21_inaction._main.view.AppFragmentPagerAdapter;
import com.rl.x.a21_inaction.achievements.view.AchievementsFragment;
import com.rl.x.a21_inaction.day_zero.view.DayZeroFragment;
import com.rl.x.a21_inaction.manager.AppManager;
import com.rl.x.a21_inaction.tasks.view.TasksFragment;

public class MainPresenter implements MainContract.Presenter {

    private FragmentActivity fragmentActivity;
    private MainModel model;
    private MainContract.View view;
    private AppManager manager;


    public MainPresenter(FragmentActivity fragmentActivity, MainContract.View view) {

        this.fragmentActivity = fragmentActivity;
        this.model = new MainModel();
        this.view = view;
        manager = new AppManager(fragmentActivity.getApplicationContext(), fragmentActivity);
    }


    /**
     * get fragmentPagerAdapter from model then pass it to view to setupTabLayoutAndViewPager
     */
    @Override
    public void setupTabLayoutAndViewPager() {

        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        AppFragmentPagerAdapter fragmentPagerAdapter = model.getFragmentPagerAdapter(supportFragmentManager);
        view.setupTabLayoutAndViewPager(fragmentPagerAdapter);
    }


    /**
     * manager responsibility
     */
    @Override
    public void goAddHabit() {

        manager.goAddHabit();
    }


    /**
     * manager responsibility
     */
    @Override
    public void clearDatabase() {

        manager.clearDatabase();
    }


    /**
     * manager responsibility
     */
    @Override
    public void stopTime() {

        manager.stopTime();
    }
}
