package com.rl.x.a21_inaction.habit.model;

import android.content.Context;

import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.day_zero.model.Expectation;
import com.rl.x.a21_inaction.habit.HabitContract;
import com.rl.x.a21_inaction.manager.AppManager;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class HabitModel implements HabitContract.Model {

    private Executor diskIOExecutor;
    private HabitDao habitDao;
    private AppManager manager;


    public HabitModel(Context applicationContext) {

        diskIOExecutor = AppExecutors.getInstance().getDiskIO();
        habitDao = AppDatabase.getInstance(applicationContext).habitDao();
        manager = new AppManager(applicationContext);
    }

    @Override
    public void saveNewHabit(String name) {

        List<Task> taskList = manager.getTaskListFromTemp();
        List<Expectation> expectationList = manager.getExpectationListFromTemp();

        final Habit habit = new Habit(name, taskList, expectationList);

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                habitDao.insertHabit(habit);
            }
        });
    }

}
