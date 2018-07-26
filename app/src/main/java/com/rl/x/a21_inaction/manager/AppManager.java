package com.rl.x.a21_inaction.manager;

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
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.model.TaskModel;
import com.rl.x.a21_inaction.add_task.model.TempTask;
import com.rl.x.a21_inaction.add_task.view.AddTaskActivity;

import java.util.ArrayList;
import java.util.List;

public class AppManager {

    private Context applicationContext;
    private TaskModel taskModel;
    private AchievementModel achievementModel;
    private ExpectationModel expectationModel;
    private AddTaskModel addTaskModel;
    private AddExpectationModel addExpectationModel;

    public AppManager(Context applicationContext) {

        this.applicationContext = applicationContext;
        taskModel = new TaskModel(applicationContext);
        achievementModel = new AchievementModel(applicationContext);
        expectationModel = new ExpectationModel(applicationContext);
        addTaskModel = new AddTaskModel(applicationContext);
        addExpectationModel = new AddExpectationModel(applicationContext);
    }

    public void addAchievement(Task swipedTask) {

        achievementModel.insertAchievement(new Achievement(swipedTask.getName()));
    }


    public void goAddTask() {

        applicationContext.startActivity(new Intent(applicationContext, AddTaskActivity.class));
    }

    public void goAddExpectation() {

        applicationContext.startActivity(new Intent(applicationContext, AddExpectationActivity.class));
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
}
