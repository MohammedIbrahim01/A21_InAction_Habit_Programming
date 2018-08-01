package com.InAction.X.x21InAction.habit.model;

import android.content.Context;

import com.InAction.X.x21InAction.database.AppDatabase;
import com.InAction.X.x21InAction.expectation.model.Expectation;
import com.InAction.X.x21InAction.habit.HabitContract;
import com.InAction.X.x21InAction.tasks.model.Task;
import com.InAction.X.x21InAction.utils.AppExecutors;

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
     * > take habit info as parameters
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
     * get tasksList from habit
     *
     * @return
     */
    @Override
    public List<Task> getTasksFromHabit() {

        haveDayTasks = false;

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                dayTasks = habitDao.getHabit().getTaskList();
                haveDayTasks = true;
            }
        });

        while (!haveDayTasks) ;

        return dayTasks;
    }


    private List<Expectation> habitExpectations = new ArrayList<>();
    private Boolean haveHabitExpectations;

    /**
     * get tasksList from habit
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

        while (!haveHabitExpectations) ;

        return habitExpectations;
    }


    private String habitName;
    private Boolean haveHabitName;

    public String getHabitName() {

        haveHabitName = false;

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                habitName = habitDao.getHabit().getName();
                haveHabitName = true;
            }
        });

        while (!haveHabitName);

        return habitName;
    }
}
