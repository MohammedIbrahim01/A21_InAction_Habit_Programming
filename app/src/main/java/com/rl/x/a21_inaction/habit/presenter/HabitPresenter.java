package com.rl.x.a21_inaction.habit.presenter;

import android.app.Activity;

import com.rl.x.a21_inaction.habit.HabitContract;
import com.rl.x.a21_inaction.habit.model.HabitModel;
import com.rl.x.a21_inaction.manager.AppManager;

public class HabitPresenter implements HabitContract.Presenter {

    private HabitModel model;
    private HabitContract.View view;
    private AppManager manager;

    public HabitPresenter(Activity activity, HabitContract.View view) {

        model = new HabitModel(activity.getApplicationContext());
        this.view = view;
        manager = new AppManager(activity.getApplicationContext(), activity);
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
    public void goAddExpectation(String habitName) {

        manager.goAddExpectation(habitName);
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
