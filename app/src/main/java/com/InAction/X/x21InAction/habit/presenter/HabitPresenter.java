package com.InAction.X.x21InAction.habit.presenter;

import android.app.Activity;
import android.content.Context;

import com.InAction.X.x21InAction.habit.HabitContract;
import com.InAction.X.x21InAction.habit.model.Habit;
import com.InAction.X.x21InAction.habit.model.HabitModel;

public class HabitPresenter implements HabitContract.Presenter {


    private HabitModel model;


    public HabitPresenter(Context applicationContext) {

        model = new HabitModel(applicationContext);
    }


    /**
     * insert Habit (Model)
     */
    @Override
    public void insertHabit(Habit habit) {

        model.insertHabit(habit);
    }
}
