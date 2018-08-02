package com.InAction.X.x21InAction.habit.presenter;

import android.app.Activity;
import android.content.Context;

import com.InAction.X.x21InAction.habit.HabitContract;
import com.InAction.X.x21InAction.habit.model.Habit;
import com.InAction.X.x21InAction.habit.model.HabitModel;
import com.InAction.X.x21InAction.habit.view.CreateHabitAdapter;
import com.InAction.X.x21InAction.habit.view.CreateHabitScreens;
import com.InAction.X.x21InAction.utils.CustomViewPager;

public class HabitPresenter implements HabitContract.Presenter {


    private HabitModel model;

    private HabitContract.View view;


    public HabitPresenter(Context applicationContext) {

        model = new HabitModel(applicationContext);
    }


    public HabitPresenter(Context applicationContext, HabitContract.View view) {

        model = new HabitModel(applicationContext);
        this.view = view;
    }

    /**
     * insert Habit (Model)
     */
    @Override
    public void insertHabit(Habit habit) {

        model.insertHabit(habit);
    }


    /**
     * get Habit (Model)
     *
     * @return
     */
    @Override
    public Habit getHabit() {

        return model.getHabit();
    }


    @Override
    public void setupAdapterWithViewPager() {

        CreateHabitAdapter adapter = view.getAdapter();
        CustomViewPager viewPager = view.getViewPager();

        adapter.addFragment(CreateHabitScreens.getScreen(0));
        adapter.addFragment(CreateHabitScreens.getScreen(1));
        adapter.addFragment(CreateHabitScreens.getScreen(2));
        adapter.addFragment(CreateHabitScreens.getScreen(3));
        adapter.addFragment(CreateHabitScreens.getScreen(4));
        adapter.addFragment(CreateHabitScreens.getScreen(5));
        adapter.addFragment(CreateHabitScreens.getScreen(6));

        viewPager.setAdapter(adapter);
    }

    @Override
    public void nextPage() {

        int page = view.getViewPagerCurrentItem();

        view.setViewPagerCurrentItem(++page);
        view.setButtonsVisibilities(page);
    }

    @Override
    public void previousPage() {

        int page = view.getViewPagerCurrentItem();

        view.setViewPagerCurrentItem(--page);
        view.setButtonsVisibilities(page);
    }
}
