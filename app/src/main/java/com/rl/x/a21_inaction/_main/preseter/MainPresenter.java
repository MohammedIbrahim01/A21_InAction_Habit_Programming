package com.rl.x.a21_inaction._main.preseter;

import android.app.Activity;

import com.rl.x.a21_inaction._main.MainContract;
import com.rl.x.a21_inaction._main.view.AppFragmentPagerAdapter;
import com.rl.x.a21_inaction.achievements.view.AchievementsFragment;
import com.rl.x.a21_inaction.day_zero.view.DayZeroFragment;
import com.rl.x.a21_inaction.manager.AppManager;
import com.rl.x.a21_inaction.tasks.view.TasksFragment;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private AppManager manager;

    public MainPresenter(Activity activity, MainContract.View view) {

        this.view = view;
        manager = new AppManager(activity.getApplicationContext(), activity);
    }

    @Override
    public void setupTabLayoutAndViewPager() {

        AppFragmentPagerAdapter adapter = new AppFragmentPagerAdapter(view.getAppSupportFragmentManager());
        adapter.addFragment(new TasksFragment(), "tasks");
        adapter.addFragment(new AchievementsFragment(), "achievements");
        adapter.addFragment(new DayZeroFragment(), "day zero");

        view.setupTabLayoutAndViewPager(adapter);
    }

    @Override
    public void goAddHabit() {

        manager.goAddHabit();
    }

    @Override
    public void displayDayTasks() {

        manager.showTaskList();
    }

    @Override
    public void scheduleDayTasks() {

        manager.scheduleDayTasks();
    }

    @Override
    public void start() {

        setupTabLayoutAndViewPager();
    }
}
