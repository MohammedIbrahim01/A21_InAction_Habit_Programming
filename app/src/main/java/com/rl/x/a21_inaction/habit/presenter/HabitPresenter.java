package com.rl.x.a21_inaction.habit.presenter;

import android.content.Context;

import com.rl.x.a21_inaction.habit.HabitContract;
import com.rl.x.a21_inaction.habit.model.HabitModel;
import com.rl.x.a21_inaction.manager.AppManager;

public class HabitPresenter implements HabitContract.Presenter {

    private HabitContract.View view;
    private HabitModel model;
    private AppManager manager;

    public HabitPresenter(Context applicationContext, HabitContract.View view) {

        this.view = view;
        model = new HabitModel(applicationContext);
        manager = new AppManager(applicationContext);
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

        model.saveNewHabit(view.getHabitName());

        view.finishActivity();
    }

    @Override
    public void start() {

    }
}
