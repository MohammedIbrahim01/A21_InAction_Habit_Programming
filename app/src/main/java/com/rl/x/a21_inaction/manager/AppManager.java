package com.rl.x.a21_inaction.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.rl.x.a21_inaction.achievements.model.Achievement;
import com.rl.x.a21_inaction.achievements.model.AchievementModel;
import com.rl.x.a21_inaction.add_expectation.model.AddExpectationModel;
import com.rl.x.a21_inaction.add_task.model.AddTaskModel;
import com.rl.x.a21_inaction.counter.presenter.CounterPresenter;
import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.expectation.model.Expectation;
import com.rl.x.a21_inaction.expectation.model.ExpectationModel;
import com.rl.x.a21_inaction.add_expectation.model.TempExpectation;
import com.rl.x.a21_inaction.add_expectation.view.AddExpectationActivity;
import com.rl.x.a21_inaction.habit.model.HabitModel;
import com.rl.x.a21_inaction.habit.view.NewHabitActivity;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.model.TaskModel;
import com.rl.x.a21_inaction.add_task.model.TempTask;
import com.rl.x.a21_inaction.add_task.view.AddTaskActivity;
import com.rl.x.a21_inaction.utils.AppExecutors;
import com.rl.x.a21_inaction.utils.Scheduler;

import java.util.ArrayList;
import java.util.List;

public class AppManager {

    public static final String NAME_HABIT = "name-habit";
    Context applicationContext;

    private Activity activity;
    private TaskModel taskModel;
    private AchievementModel achievementModel;
    private ExpectationModel expectationModel;
    private AddTaskModel addTaskModel;
    private AddExpectationModel addExpectationModel;
    private HabitModel habitModel;

    private CounterPresenter counterPresenter;


    /**
     * normal constructor to use when there is no navigation need
     *
     * @param applicationContext
     */
    public AppManager(Context applicationContext) {

        initAllModels(applicationContext);
    }


    /**
     * special constructor to use when there is navigation need
     *
     * @param applicationContext
     * @param activity
     */
    public AppManager(Context applicationContext, Activity activity) {

        this.activity = activity;
        initAllModels(applicationContext);
    }


    /**
     * to use in constructors
     *
     * @param applicationContext
     */
    private void initAllModels(Context applicationContext) {

        this.applicationContext = applicationContext;
        taskModel = new TaskModel(applicationContext);
        achievementModel = new AchievementModel(applicationContext);
        expectationModel = new ExpectationModel(applicationContext);
        addTaskModel = new AddTaskModel(applicationContext);
        addExpectationModel = new AddExpectationModel(applicationContext);
        habitModel = new HabitModel(applicationContext);

        counterPresenter = new CounterPresenter(applicationContext);
    }


    /********************************************************* Navigation *************************************************/


    /**
     * navigate to AddTaskActivity
     */
    public void goAddTask(String habitName) {

        Intent intent = new Intent(activity, AddTaskActivity.class);
        intent.putExtra(NAME_HABIT, habitName);
        activity.startActivity(intent);
    }


    /**
     * navigate to AddExpectationActivity
     */
    public void goAddExpectation(String habitName) {

        Intent intent = new Intent(activity, AddTaskActivity.class);
        intent.putExtra(NAME_HABIT, habitName);
        activity.startActivity(new Intent(activity, AddExpectationActivity.class));
    }


    /**
     * navigate to NewHabitActivity
     */
    public void goAddHabit() {

        activity.startActivity(new Intent(activity, NewHabitActivity.class));
    }


    /****************************************************** External Methods **************************************************/


    /**
     * To Use In TaskPresenter
     * <p>
     * insert achievement created from swiped task
     *
     * @param task
     */
    public void addAchievementFromTask(Task task) {

        achievementModel.insertAchievement(new Achievement(task.getName()));
    }


    /**
     * To Use In HabitPresenter
     * <p>
     * get taskList from tempTasks
     *
     * @return Task List
     */
    public List<Task> getTaskListFromTemp() {

        List<Task> taskList = new ArrayList<>();
        List<TempTask> tempTaskList = addTaskModel.getTempTaskList();

        for (TempTask tempTask : tempTaskList) {

            taskList.add(new Task(tempTask.getName(), tempTask.getCalendar(), tempTask.getHabitName()));
        }

        return taskList;
    }


    /**
     * To Use In HabitPresenter
     * <p>
     * get ExpectationList from tempExpectations
     *
     * @return Expectation List
     */
    public List<Expectation> getExpectationListFromTemp() {

        List<Expectation> expectationList = new ArrayList<>();
        List<TempExpectation> tempExpectationList = addExpectationModel.getTempExpectationList();

        for (TempExpectation tempExpectation : tempExpectationList) {

            expectationList.add(new Expectation(tempExpectation.getName(), tempExpectation.getHabitName()));
        }

        return expectationList;
    }


    /**
     * To Use In HabitPresenter
     * <p>
     * show expectations
     * show tasks
     * schedule tasks
     */
    public void startHabitPrograming() {

        showExpectationList(getExpectationListFromHabit());
        showTaskList(getTaskListFromHabit());
        scheduleDayTasks(getTaskListFromHabit());
        startCounter();
    }


    /**     To Use In MainPresenter
     *
     * stop day counting (cancel alarm with the same PendingIntent)
     */
    public void stopTime() {


    }


    public void newDay() {

        showTaskList(getTaskListFromHabit());
        scheduleDayTasks(getTaskListFromHabit());
    }


    /****************************************************** Inner Methods **************************************************/


    private void startCounter() {

        counterPresenter.startCountingIfMidnight();
    }


    /**
     * show tasks in tasks Tab
     */
    public void showTaskList(List<Task> taskList) {

        taskModel.insertTaskList(taskList);
    }


    /**
     * show expectations in DayZero Tab
     */
    public void showExpectationList(List<Expectation> expectationList) {

        expectationModel.insertExpectationList(expectationList);
    }


    /**
     * schedule day tasks each in its own time
     *
     * @param taskList
     */
    public void scheduleDayTasks(List<Task> taskList) {

        Scheduler scheduler = new Scheduler(applicationContext);

        for (Task task : taskList) {

            scheduler.schedule(task);
        }
    }


    /**
     * get dayTasks that stores inside the habit
     *
     * @return Task List
     */
    public List<Task> getTaskListFromHabit() {

        return habitModel.getTasksFromHabit();
    }

    /**
     * get habitExpectations that stores inside the habit
     *
     * @return Expectation List
     */
    public List<Expectation> getExpectationListFromHabit() {

        return habitModel.getExpectationsFromHabit();
    }


    /**
     * clear database
     */
    public void clearDatabase() {

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {

                AppDatabase.getInstance(applicationContext).clearAllTables();
            }
        });
    }
}
