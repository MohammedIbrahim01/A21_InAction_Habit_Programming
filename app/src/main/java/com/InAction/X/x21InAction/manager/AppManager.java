package com.InAction.X.x21InAction.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.InAction.X.x21InAction.AppCP;
import com.InAction.X.x21InAction.achievements.communication.AchievementCommunication;
import com.InAction.X.x21InAction.achievements.model.Achievement;
import com.InAction.X.x21InAction.counter.model.CounterModel;
import com.InAction.X.x21InAction.counter.presenter.CounterPresenter;
import com.InAction.X.x21InAction.expectation.communication.ExpectationCommunication;
import com.InAction.X.x21InAction.habit.communication.HabitCommunication;
import com.InAction.X.x21InAction.habit.model.Habit;
import com.InAction.X.x21InAction.habit.view.CreateHabitActivity;
import com.InAction.X.x21InAction.tasks.communication.TaskCommunication;
import com.InAction.X.x21InAction.temp_expectation.communication.TempExpectationCommunication;
import com.InAction.X.x21InAction.temp_task.communication.TempTaskCommunication;
import com.InAction.X.x21InAction.database.AppDatabase;
import com.InAction.X.x21InAction.expectation.model.Expectation;
import com.InAction.X.x21InAction.temp_expectation.model.TempExpectation;
import com.InAction.X.x21InAction.temp_expectation.view.AddExpectationActivity;
import com.InAction.X.x21InAction.tasks.model.Task;
import com.InAction.X.x21InAction.temp_task.model.TempTask;
import com.InAction.X.x21InAction.temp_task.view.AddTaskActivity;
import com.InAction.X.x21InAction.utils.AppExecutors;
import com.InAction.X.x21InAction.utils.NotificationsUtils;
import com.InAction.X.x21InAction.utils.Scheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AppManager {


    public static final String NAME_SHARED_PREFERENCES = AppCP.NAME_SHARED_PREFERENCES;
    public static final String KEY_FIRST_LAUNCH = AppCP.KEY_FIRST_LAUNCH;
    public static final String KEY_HABIT_NAME = AppCP.KEY_HABIT_NAME;



    /********************************************************* Navigation *************************************************/


    /**
     * navigate to AddTaskActivity
     */
    public static void goAddTask(Activity currentActivity, String habitName) {

        Intent intent = new Intent(currentActivity, AddTaskActivity.class);
        intent.putExtra(KEY_HABIT_NAME, habitName);
        currentActivity.startActivity(intent);
    }


    /**
     * navigate to AddExpectationActivity
     */
    public static void goAddExpectation(Activity currentActivity, String habitName) {

        Intent intent = new Intent(currentActivity, AddExpectationActivity.class);
        intent.putExtra(KEY_HABIT_NAME, habitName);
        currentActivity.startActivity(intent);
    }


    /**
     * navigate to CreateHabitActivity
     *
     * @param currentActivity
     */
    public static void goCreateHabit(Activity currentActivity) {

        currentActivity.startActivity(new Intent(currentActivity, CreateHabitActivity.class));
    }


    /****************************************************** External Methods **************************************************/


    /**
     * To Use In TaskPresenter
     * <p>
     * insert achievement created from swiped task
     *
     * @param task
     */
    public static void addAchievementFromTask(Context context, Task task) {

        int day = new CounterModel(context).getCount();
        AchievementCommunication achievementCommunication = new AchievementCommunication(context);

        achievementCommunication.insertAchievement(new Achievement(task.getName(), day));
    }


    /**
     * To Use In HabitPresenter
     * <p>
     * getScreen taskList from tempTasks
     *
     * @return Task List
     */
    public static List<Task> getTaskListFromTemp(Context context) {

        TempTaskCommunication tempTaskCommunication = new TempTaskCommunication(context);

        List<Task> taskList = new ArrayList<>();
        List<TempTask> tempTaskList = tempTaskCommunication.getTempTaskList();

        for (TempTask tempTask : tempTaskList) {

            taskList.add(new Task(tempTask.getName(), tempTask.getCalendar(), tempTask.getHabitName()));
            Log.i("WWW", "getTaskListFromTemp: habit name >>> " + tempTask.getHabitName());
        }

        return taskList;
    }


    /**
     * To Use In HabitPresenter
     * <p>
     * getScreen ExpectationList from tempExpectations
     *
     * @return Expectation List
     */
    public static List<Expectation> getExpectationListFromTemp(Context context) {

        TempExpectationCommunication tempExpectationCommunication = new TempExpectationCommunication(context);

        List<Expectation> expectationList = new ArrayList<>();
        List<TempExpectation> tempExpectationList = tempExpectationCommunication.getTempExpectationList();

        for (TempExpectation tempExpectation : tempExpectationList) {

            expectationList.add(new Expectation(tempExpectation.getName(), tempExpectation.getHabitName()));
            Log.i("WWW", "getExpectationListFromTemp: habit name >>> ");
        }

        return expectationList;
    }


    /**
     * To Use In HabitPresenter
     *
     * @param habitName
     */
    public static void startHabitPrograming(Context context, String habitName) {

        Habit habit = new Habit(habitName, getTaskListFromTemp(context), getExpectationListFromTemp(context));
        saveHabit(context, habit);
        showExpectationList(context, getExpectationListFromHabit(context));
        new CounterPresenter(context).startCountingIfMidnight();
    }


    /**
     * To Use In MainPresenter
     * <p>
     * getScreen current count of days
     *
     * @return
     */
    public static String getCount(Context context) {

        return String.valueOf(new CounterModel(context).getCount());
    }


    /**
     * To Use In CounterPresenter
     *
     * @return
     */
    public static String getHabitName(Context context) {

        HabitCommunication habitCommunication = new HabitCommunication(context);
        return habitCommunication.getHabit().getName();
    }


    /**
     * To Use In CounterPresenter
     */
    public static void startFirstDay(Context context) {

        List<Task> taskList = getTaskListFromHabit(context);

        showTaskList(context, taskList);
        scheduleDayTasks(context, taskList);
    }


    /**
     * To Use In CounterReceiver
     */
    public static void newDay(Context context) {

        clearLateTasks(context);               // if there


        List<Task> taskList = getTaskListFromHabit(context);

        showTaskList(context, taskList);
        scheduleDayTasks(context, taskList);
    }


    /**
     * To Use In MainActivity
     */
    public static void resetCounter(Context context) {

        new CounterModel(context).resetCounter();
    }


    /****************************************************** Inner Methods **************************************************/


    public static void saveHabit(Context context, Habit habit) {

        HabitCommunication habitCommunication = new HabitCommunication(context);

        habitCommunication.insertHabit(habit);
    }


    private static void clearLateTasks(Context context) {

        TaskCommunication taskCommunication = new TaskCommunication(context);

        taskCommunication.deleteAllTasks();
    }


    /**
     * show tasks in tasks Tab
     */
    public static void showTaskList(Context context, List<Task> taskList) {

        TaskCommunication taskCommunication = new TaskCommunication(context);

        taskCommunication.insertTaskList(taskList);
    }


    /**
     * show expectations in DayZero Tab
     */
    public static void showExpectationList(Context context, List<Expectation> expectationList) {

        ExpectationCommunication expectationCommunication = new ExpectationCommunication(context);

        expectationCommunication.insertExpectationList(expectationList);
    }


    /**
     * scheduleTask day tasks each in its own time
     *
     * @param taskList
     */
    public static void scheduleDayTasks(Context context, List<Task> taskList) {

        Scheduler scheduler = new Scheduler(context);

        for (Task task : taskList) {

            task.getCalendar().set(Calendar.DAY_OF_YEAR, Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
            scheduler.scheduleTask(task);
        }
    }


    /**
     * getScreen dayTasks that stores inside the habit
     *
     * @return Task List
     */
    public static List<Task> getTaskListFromHabit(Context context) {

        HabitCommunication habitCommunication = new HabitCommunication(context);

        return habitCommunication.getHabit().getTaskList();
    }


    /**
     * getScreen habitExpectations that stores inside the habit
     *
     * @return Expectation List
     */
    public static List<Expectation> getExpectationListFromHabit(Context context) {

        HabitCommunication habitCommunication = new HabitCommunication(context);

        return habitCommunication.getHabit().getExpectationList();
    }


    /**
     * clear database
     */
    public static void clearDatabase(final Context context) {

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {

                AppDatabase.getInstance(context).clearAllTables();
            }
        });
    }


    public static void setFirstLaunch(Context context, boolean firstLaunch) {

        context.getSharedPreferences(NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE).edit().putBoolean(KEY_FIRST_LAUNCH, false).apply();

    }


    public static void stopCounter(Context context) {

        new CounterPresenter(context).stopCounter();
        new NotificationsUtils(context).notifyCountingEnd(getHabitName(context));
    }
}
