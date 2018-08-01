package com.InAction.X.x21InAction.manager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.InAction.X.x21InAction.achievements.model.Achievement;
import com.InAction.X.x21InAction.achievements.model.AchievementModel;
import com.InAction.X.x21InAction.temp_expectation.model.TempExpectationModel;
import com.InAction.X.x21InAction.temp_task.model.TempTaskModel;
import com.InAction.X.x21InAction.counter.model.CounterModel;
import com.InAction.X.x21InAction.counter.presenter.CounterPresenter;
import com.InAction.X.x21InAction.counter.receiver.CounterReceiver;
import com.InAction.X.x21InAction.database.AppDatabase;
import com.InAction.X.x21InAction.expectation.model.Expectation;
import com.InAction.X.x21InAction.expectation.model.ExpectationModel;
import com.InAction.X.x21InAction.temp_expectation.model.TempExpectation;
import com.InAction.X.x21InAction.temp_expectation.view.AddExpectationActivity;
import com.InAction.X.x21InAction.habit.model.HabitModel;
import com.InAction.X.x21InAction.tasks.model.Task;
import com.InAction.X.x21InAction.tasks.model.TaskModel;
import com.InAction.X.x21InAction.temp_task.model.TempTask;
import com.InAction.X.x21InAction.temp_task.view.AddTaskActivity;
import com.InAction.X.x21InAction.utils.AppExecutors;
import com.InAction.X.x21InAction.utils.Scheduler;

import java.util.ArrayList;
import java.util.List;

public class AppManager {

    public static final String KEY_NAME_HABIT = "key-name-habit";
    private Context applicationContext;

    private Activity activity;
    private TaskModel taskModel;
    private AchievementModel achievementModel;
    private ExpectationModel expectationModel;
    private TempTaskModel tempTaskModel;
    private TempExpectationModel tempExpectationModel;
    private HabitModel habitModel;
    private CounterModel counterModel;

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
        counterPresenter = new CounterPresenter(applicationContext);
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
        tempTaskModel = new TempTaskModel(applicationContext);
        tempExpectationModel = new TempExpectationModel(applicationContext);
        habitModel = new HabitModel(applicationContext);
        counterModel = new CounterModel(applicationContext);
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
        List<TempTask> tempTaskList = tempTaskModel.getTempTaskList();

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
        List<TempExpectation> tempExpectationList = tempExpectationModel.getTempExpectationList();

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
     * scheduleTask tasks
     */
    public void startHabitPrograming() {

        startCounter();
    }


    /**
     * To Use In MainPresenter
     * <p>
     * stop day counting (cancel alarm with the same PendingIntent)
     */
    public void stopTime() {

        //cancel alarm that fires CounterReceiver
        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(applicationContext, CounterReceiver.class);
        PendingIntent operation = PendingIntent.getBroadcast(applicationContext, 44, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(operation);
    }


    /**
     * To Use In MainPresenter
     * <p>
     * get current count of days
     *
     * @return
     */
    public String getCount() {

        return String.valueOf(counterModel.getCount());
    }


    /**
     * To Use In CounterPresenter
     *
     * @return
     */
    public String getHabitName() {

        return habitModel.getHabit().getName();
    }


    /**
     * To Use In CounterPresenter
     */
    public void startFirstDay() {

        showExpectationList(getExpectationListFromHabit());
        showTaskList(getTaskListFromHabit());
        scheduleDayTasks(getTaskListFromHabit());
    }


    /**
     * To Use In CounterReceiver
     */
    public void newDay() {

        List<Task> taskList = getTaskListFromHabit();

        unScheduleLateTasks(taskList);  // if there
        clearLateTasks();               // if there

        showTaskList(taskList);
        scheduleDayTasks(taskList);
    }


    /**
     * To Use In MainActivity
     */
    public void resetCounter() {

        counterModel.resetCounter();
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

        taskModel.deleteAllTasks();
    }


    private void startCounter() {

//        counterPresenter.startCountingIfMidnight();
        counterPresenter.startCountingIf(18);
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
     * get dayTasks that stores inside the habit
     *
     * @return Task List
     */
    public List<Task> getTaskListFromHabit() {

        return habitModel.getHabit().getTaskList();
    }

    /**
     * get habitExpectations that stores inside the habit
     *
     * @return Expectation List
     */
    public List<Expectation> getExpectationListFromHabit() {

        return habitModel.getHabit().getExpectationList();
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
