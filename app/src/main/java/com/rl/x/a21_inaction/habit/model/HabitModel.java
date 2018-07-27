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

    public HabitModel(Context applicationContext) {

        diskIOExecutor = AppExecutors.getInstance().getDiskIO();
        habitDao = AppDatabase.getInstance(applicationContext).habitDao();
    }

    /**
     * > take parameters of habit fields
     * > create new Habit with this info
     * > then insert new Habit in database
     *
     * @param name
     * @param taskListFromTemp
     * @param expectationListFromTemp
     */
    @Override
    public void saveNewHabit(String name, List<Task> taskListFromTemp, List<Expectation> expectationListFromTemp) {

        List<Task> taskList = taskListFromTemp;
        List<Expectation> expectationList = expectationListFromTemp;

        final Habit habit = new Habit(name, taskList, expectationList);

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                habitDao.insertHabit(habit);
            }
        });
    }


    private List<Task> dayTasks = new ArrayList<>();
    private Boolean haveDayTasks;

    /**
     * get tasksList from the only habit that stored in habits table in the database
     *
     * @return
     */
    @Override
    public List<Task> getTasksFromHabit() {

        haveDayTasks = false;

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                dayTasks = habitDao.getAllHabits().get(0).getTaskList();
                haveDayTasks = true;
            }
        });

        while (!haveDayTasks);

        return dayTasks;
    }


    private List<Expectation> habitExpectations = new ArrayList<>();
    private Boolean haveHabitExpectations;

    /**
     * get tasksList from the only habit that stored in habits table in the database
     *
     * @return
     */
    @Override
    public List<Expectation> getExpectationsFromHabit() {

        haveHabitExpectations = false;

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                habitExpectations = habitDao.getHabit().getExpectationList();
                haveHabitExpectations = true;
            }
        });

        while (!haveHabitExpectations);

        return habitExpectations;
    }
}
