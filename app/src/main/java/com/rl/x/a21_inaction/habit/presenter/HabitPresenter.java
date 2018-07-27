package com.rl.x.a21_inaction.habit.presenter;

import android.app.Activity;

import com.rl.x.a21_inaction.habit.HabitContract;
import com.rl.x.a21_inaction.habit.model.HabitModel;
import com.rl.x.a21_inaction.manager.AppManager;

public class HabitPresenter implements HabitContract.Presenter {

    private HabitModel model;
    private HabitContract.View view;
    private AppManager manager;

    public HabitPresenter(Activity newHabitActivity, HabitContract.View view) {

        model = new HabitModel(newHabitActivity.getApplicationContext());
        this.view = view;
        manager = new AppManager(newHabitActivity.getApplicationContext(), newHabitActivity);
    }


    /**
     * manager responsibility
     */
    @Override
    public void goAddTask(String habitName) {

        manager.goAddTask(habitName);
    }

    /**
     * manager responsibility
     */
    @Override
    public void goAddExpectation() {

        manager.goAddExpectation();
    }

    /**
     * tell the model to save new habit with newHabitName and with tasksFromTempTasks (from manager)
     * and with expectationsFromTempExpectations (from manager)
     *
     * then showExpectationList to display it on DayZero Tab
     *
     * then finish NewHabit Activity
     *
     */
    @Override
    public void saveNewHabit() {

        model.saveNewHabit(view.getHabitName(), manager.getTaskListFromTemp(), manager.getExpectationListFromTemp());

        manager.startHabitPrograming();

        view.finishActivity();
    }


    @Override
    public void start() {

    }
}
