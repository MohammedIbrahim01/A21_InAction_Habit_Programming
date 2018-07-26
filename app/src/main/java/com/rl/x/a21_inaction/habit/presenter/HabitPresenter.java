package com.rl.x.a21_inaction.habit.presenter;

import android.app.Activity;
import android.content.Context;

import com.rl.x.a21_inaction.habit.HabitContract;
import com.rl.x.a21_inaction.habit.model.HabitModel;
import com.rl.x.a21_inaction.manager.AppManager;

public class HabitPresenter implements HabitContract.Presenter {

    private HabitContract.View view;
    private HabitModel model;
    private AppManager manager;

    public HabitPresenter(Activity newHabitActivity, HabitContract.View view) {

        this.view = view;
        model = new HabitModel(newHabitActivity.getApplicationContext());
        manager = new AppManager(newHabitActivity.getApplicationContext(), newHabitActivity);
    }


    @Override
    public void goAddTask() {

        manager.goAddTask();
    }

    @Override
    public void goAddExpectation() {

        manager.goAddExpectation();
    }

    @Override
    public void saveNewHabit() {

        model.saveNewHabit(view.getHabitName(), manager.getTaskListFromTemp(), manager.getExpectationListFromTemp());

        manager.insertExpectationList();

        view.finishActivity();
    }

    @Override
    public void start() {

    }
}
