package com.rl.x.a21_inaction.habit.presenter;

import android.app.Activity;

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


    /**
     * navigate to AddTask Screen
     */
    @Override
    public void goAddTask(String habitName) {

        manager.goAddTask(habitName);
    }

    /**
     * navigate to AddExpectation Screen
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

        model.saveNewHabit(view.getNewHabitName(), manager.getTaskListFromTemp(), manager.getExpectationListFromTemp());

        manager.showExpectationList();

        view.finishActivity();
    }


    @Override
    public void start() {

    }
}
