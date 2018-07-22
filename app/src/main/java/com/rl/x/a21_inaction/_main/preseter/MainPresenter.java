package com.rl.x.a21_inaction._main.preseter;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.rl.x.a21_inaction._main.MainContract;
import com.rl.x.a21_inaction._main.view.AppFragmentPagerAdapter;
import com.rl.x.a21_inaction._main.view.MainActivity;
import com.rl.x.a21_inaction.achievements.view.AchievementsFragment;
import com.rl.x.a21_inaction.day_zero.view.DayZeroFragment;
import com.rl.x.a21_inaction.tasks.view.TasksFragment;

public class MainPresenter implements MainContract.Presenter {

    private FragmentActivity fragmentActivity;

    public MainPresenter(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public void setupTabLayoutAndViewPager(ViewPager viewPager, TabLayout tabLayout) {
        AppFragmentPagerAdapter adapter = new AppFragmentPagerAdapter(fragmentActivity.getSupportFragmentManager());
        adapter.addFragment(new TasksFragment(), "tasks");
        adapter.addFragment(new AchievementsFragment(), "achievements");
        adapter.addFragment(new DayZeroFragment(), "day zero");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
