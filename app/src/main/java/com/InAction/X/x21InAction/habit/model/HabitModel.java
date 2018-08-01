package com.InAction.X.x21InAction.habit.model;

import android.content.Context;

import com.InAction.X.x21InAction.database.AppDatabase;
import com.InAction.X.x21InAction.expectation.model.Expectation;
import com.InAction.X.x21InAction.habit.HabitContract;
import com.InAction.X.x21InAction.tasks.model.Task;
import com.InAction.X.x21InAction.utils.AppExecutors;

import java.util.List;
import java.util.concurrent.Executor;

public class HabitModel implements HabitContract.Model {


    private Executor diskIOExecutor;
    private HabitDao habitDao;


    public HabitModel(Context applicationContext) {

        diskIOExecutor = AppExecutors.getInstance().getDiskIO();
        habitDao = AppDatabase.getInstance(applicationContext).habitDao();
    }


    /**
     * insert Habit
     */
    @Override
    public void insertHabit(final Habit habit) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                habitDao.insertHabit(habit);
            }
        });
    }


    /**
     * get the only Habit
     */
    private Habit habit;
    private Boolean haveHabit;

    @Override
    public Habit getHabit() {

        haveHabit = false;

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                habit = habitDao.getHabit();
                haveHabit = true;
            }
        });


        while (!haveHabit) ;

        return habit;
    }
}
