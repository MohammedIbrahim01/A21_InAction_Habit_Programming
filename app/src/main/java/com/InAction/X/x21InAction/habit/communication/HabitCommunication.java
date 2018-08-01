package com.InAction.X.x21InAction.habit.communication;

import android.content.Context;

import com.InAction.X.x21InAction.habit.HabitContract;
import com.InAction.X.x21InAction.habit.model.Habit;
import com.InAction.X.x21InAction.habit.presenter.HabitPresenter;

public class HabitCommunication implements HabitContract.Communication {


    private HabitPresenter presenter;


    public HabitCommunication(Context applicationContext) {

        presenter = new HabitPresenter(applicationContext);
    }


    @Override
    public void insertHabit(Habit habit) {

        presenter.insertHabit(habit);
    }
}
