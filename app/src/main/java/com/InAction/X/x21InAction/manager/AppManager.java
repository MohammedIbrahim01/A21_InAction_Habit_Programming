package com.InAction.X.x21InAction.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.InAction.X.x21InAction.achievements.communication.AchievementCommunication;
import com.InAction.X.x21InAction.achievements.model.Achievement;
import com.InAction.X.x21InAction.counter.Communication.CounterCommunication;
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
import com.InAction.X.x21InAction.utils.Scheduler;

import java.util.ArrayList;
import java.util.List;

public class AppManager {


    public static final String NAME_SHARED_PREFERENCES = "name-sharedPreferences";
    public static final String KEY_FIRST_LAUNCH = "key-first_launch";
    public static final String KEY_NAME_HABIT = "key-name-habit";


    private Context applicationContext;
    private Activity activity;

    private TaskCommunication taskCommunication;
    private AchievementCommunication achievementCommunication;
    private ExpectationCommunication expectationCommunication;
    private TempTaskCommunication tempTaskCommunication;
    private TempExpectationCommunication tempExpectationCommunication;
    private HabitCommunication habitCommunication;
    private CounterCommunication counterCommunication;


    /**
     * normal constructor to use when there is no navigation need
     *
     * @param applicationContext
     */
    public AppManager(Context applicationContext) {

        initAllCommunications(applicationContext);
    }


    /**
     * special constructor to use when there is navigation need
     *
     * @param applicationContext
     * @param activity
     */
    public AppManager(Context applicationContext, Activity activity) {

        this.activity = activity;
        initAllCommunications(applicationContext);
    }


    /**
     * to use in constructors
     *
     * @param applicationContext
     */
    private void initAllCommunications(Context applicationContext) {

        this.applicationContext = applicationContext;
        taskCommunication = new TaskCommunication(applicationContext);
        achievementCommunication = new AchievementCommunication(applicationContext);
        expectationCommunication = new ExpectationCommunication(applicationContext);
        tempTaskCommunication = new TempTaskCommunication(applicationContext);
        tempExpectationCommunication = new TempExpectationCommunication(applicationContext);
        habitCommunication = new HabitCommunication(applicationContext);
        counterCommunication = new CounterCommunication(applicationContext);
    }


    /********************************************************* Navigation *************************************************/


    /**
     * navigate to AddTaskActivity
     */
    public void goAddTask(String habitName) {

        Intent intent = new Intent(activity, AddTaskActivity.class);
        intent.putExtra(KEY_NAME_HABIT, habitName);
        activity.startActivity(intent);
    }


    /**
     * navigate to AddExpectationActivity
     */
    public void goAddExpectation(String habitName) {

        Intent intent = new Intent(activity, AddExpectationActivity.class);
        intent.putExtra(KEY_NAME_HABIT, habitName);
        activity.startActivity(intent);
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

        int day = counterCommunication.getCount();
        achievementCommunication.insertAchievement(new Achievement(task.getName(), day));
    }


    /**
     * To Use In HabitPresenter
     * <p>
     * getScreen taskList from tempTasks
     *
     * @return Task List
     */
    public List<Task> getTaskListFromTemp() {

        List<Task> taskList = new ArrayList<>();
        List<TempTask> tempTaskList = tempTaskCommunication.getTempTaskList();

        for (TempTask tempTask : tempTaskList) {

            taskList.add(new Task(tempTask.getName(), tempTask.getCalendar(), tempTask.getHabitName()));
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
    public List<Expectation> getExpectationListFromTemp() {

        List<Expectation> expectationList = new ArrayList<>();
        List<TempExpectation> tempExpectationList = tempExpectationCommunication.getTempExpectationList();

        for (TempExpectation tempExpectation : tempExpectationList) {

            expectationList.add(new Expectation(tempExpectation.getName(), tempExpectation.getHabitName()));
        }

        return expectationList;
    }


    /**
     * To Use In HabitPresenter
     *
     * @param habitName
     */
    public void startHabitPrograming(String habitName) {

        Habit habit = new Habit(habitName, getTaskListFromTemp(), getExpectationListFromTemp());
        saveHabit(habit);
        showExpectationList(getExpectationListFromHabit());
        counterCommunication.startCountingIfMidnight(this);
    }


    /**
     * To Use In MainPresenter
     * <p>
     * getScreen current count of days
     *
     * @return
     */
    public String getCount() {

        return String.valueOf(counterCommunication.getCount());
    }


    /**
     * To Use In CounterPresenter
     *
     * @return
     */
    public String getHabitName() {

        return habitCommunication.getHabit().getName();
    }


    /**
     * To Use In CounterPresenter
     */
    public void startFirstDay() {

        List<Task> taskList = getTaskListFromHabit();

        showTaskList(taskList);
        scheduleDayTasks(taskList);
    }


    /**
     * To Use In CounterReceiver
     */
    public void newDay() {

        unScheduleLateTasks(getTaskListFromTasks());  // if there
        clearLateTasks();               // if there


        List<Task> taskList = getTaskListFromHabit();

        showTaskList(taskList);
        scheduleDayTasks(taskList);
    }


    public void saveHabit(Habit habit){

        habitCommunication.insertHabit(habit);
    }


    /**
     * To Use In MainActivity
     */
    public void resetCounter() {

        counterCommunication.resetCounter();
    }


    /****************************************************** Inner Methods **************************************************/


    /**
     * unScheduleTask late tasks
     *
     * @param taskList
     */
    private void unScheduleLateTasks(List<Task> taskList) {

        Scheduler scheduler = new Scheduler(applicationContext);

        for (Task task : taskList) {

            scheduler.unScheduleTask(task);
        }
    }

    private void clearLateTasks() {

        taskCommunication.deleteAllTasks();
    }


    /**
     * show tasks in tasks Tab
     */
    public void showTaskList(List<Task> taskList) {

        taskCommunication.insertTaskList(taskList);
    }


    /**
     * show expectations in DayZero Tab
     */
    public void showExpectationList(List<Expectation> expectationList) {

        expectationCommunication.insertExpectationList(expectationList);
    }


    /**
     * scheduleTask day tasks each in its own time
     *
     * @param taskList
     */
    public void scheduleDayTasks(List<Task> taskList) {

        Scheduler scheduler = new Scheduler(applicationContext);

        for (Task task : taskList) {

            scheduler.scheduleTask(task);
        }
    }


    /**
     * getScreen dayTasks that stores inside the habit
     *
     * @return Task List
     */
    public List<Task> getTaskListFromHabit() {

        return habitCommunication.getHabit().getTaskList();
    }

    /**
     * getScreen habitExpectations that stores inside the habit
     *
     * @return Expectation List
     */
    public List<Expectation> getExpectationListFromHabit() {

        return habitCommunication.getHabit().getExpectationList();
    }


    /**
     * getScreen dayTasks from Task database
     *
     * to unSchedule it when new day is come
     *
     * @return Task List
     */
    public List<Task> getTaskListFromTasks() {

        return taskCommunication.getAllTasks();
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


    public void setFirstLaunch(boolean firstLaunch) {

        applicationContext.getSharedPreferences(NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE).edit().putBoolean(KEY_FIRST_LAUNCH, false).apply();

    }


    public void goCreateHabit() {

        activity.startActivity(new Intent(activity, CreateHabitActivity.class));
    }
}
