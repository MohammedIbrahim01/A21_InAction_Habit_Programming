package com.rl.x.a21_inaction.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.rl.x.a21_inaction.achievements.model.Achievement;
import com.rl.x.a21_inaction.achievements.model.AchievementModel;
import com.rl.x.a21_inaction.add_expectation.model.AddExpectationModel;
import com.rl.x.a21_inaction.add_task.model.AddTaskModel;
import com.rl.x.a21_inaction.day_zero.model.Expectation;
import com.rl.x.a21_inaction.day_zero.model.ExpectationModel;
import com.rl.x.a21_inaction.add_expectation.model.TempExpectation;
import com.rl.x.a21_inaction.add_expectation.view.AddExpectationActivity;
import com.rl.x.a21_inaction.habit.model.HabitModel;
import com.rl.x.a21_inaction.habit.view.NewHabitActivity;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.model.TaskModel;
import com.rl.x.a21_inaction.add_task.model.TempTask;
import com.rl.x.a21_inaction.add_task.view.AddTaskActivity;

import java.util.ArrayList;
import java.util.List;

public class AppManager {

    private Context applicationContext;
    private Activity newHabitActivity;
    private TaskModel taskModel;
    private AchievementModel achievementModel;
    private ExpectationModel expectationModel;
    private AddTaskModel addTaskModel;
    private AddExpectationModel addExpectationModel;
    private HabitModel habitModel;

    public AppManager(Context applicationContext) {

        this.applicationContext = applicationContext;
        taskModel = new TaskModel(applicationContext);
        achievementModel = new AchievementModel(applicationContext);
        expectationModel = new ExpectationModel(applicationContext);
        addTaskModel = new AddTaskModel(applicationContext);
        addExpectationModel = new AddExpectationModel(applicationContext);
        habitModel = new HabitModel(applicationContext);
    }


    public AppManager(Context applicationContext, Activity newHabitActivity) {

        this.newHabitActivity = newHabitActivity;
        this.applicationContext = applicationContext;
        taskModel = new TaskModel(applicationContext);
        achievementModel = new AchievementModel(applicationContext);
        expectationModel = new ExpectationModel(applicationContext);
        addTaskModel = new AddTaskModel(applicationContext);
        addExpectationModel = new AddExpectationModel(applicationContext);
        habitModel = new HabitModel(applicationContext);
    }


    public void addAchievement(Task swipedTask) {

        achievementModel.insertAchievement(new Achievement(swipedTask.getName()));
    }


    public void goAddTask() {

        newHabitActivity.startActivity(new Intent(newHabitActivity, AddTaskActivity.class));
    }

    public void goAddExpectation() {

        newHabitActivity.startActivity(new Intent(newHabitActivity, AddExpectationActivity.class));
    }

    public List<Task> getTaskListFromTemp() {

        List<Task> taskList = new ArrayList<>();
        List<TempTask> tempTaskList = addTaskModel.getTempTaskList();

        for (TempTask tempTask : tempTaskList) {

            taskList.add(new Task(tempTask.getName()));
        }

        return taskList;
    }

    public List<Expectation> getExpectationListFromTemp() {

        List<Expectation> expectationList = new ArrayList<>();
        List<TempExpectation> tempExpectationList = addExpectationModel.getTempExpectationList();

        for (TempExpectation tempExpectation : tempExpectationList) {

            expectationList.add(new Expectation(tempExpectation.getName()));
        }

        return expectationList;
    }


    public List<Task> getTaskListFromHabit() {

        return habitModel.getDayTasks();
    }

    public void insertTaskList() {

        taskModel.insertTaskList(getTaskListFromHabit());
    }

    public List<Expectation> getExpectationListFromHabit() {

        return habitModel.getHabitExpectations();
    }

    public void insertExpectationList() {

        expectationModel.insertExpectationList(getExpectationListFromHabit());
    }
}
