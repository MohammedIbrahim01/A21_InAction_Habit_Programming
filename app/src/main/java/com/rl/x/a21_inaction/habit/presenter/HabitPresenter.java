package com.rl.x.a21_inaction.habit.presenter;

import android.content.Context;

import com.rl.x.a21_inaction.habit.HabitContract;
import com.rl.x.a21_inaction.habit.model.HabitModel;
import com.rl.x.a21_inaction.habit.model.TempExpectation;
import com.rl.x.a21_inaction.habit.model.TempTask;

public class HabitPresenter implements HabitContract.Presenter {

    private HabitContract.AddTaskView addTaskView;
    private HabitContract.NewHabitView newHabitView;
    private HabitContract.AddExpectationView addExpectationView;
    private HabitModel model;

    public HabitPresenter(Context applicationContext) {

        model = new HabitModel(applicationContext);
    }

    public void setAddExpectationView(HabitContract.AddExpectationView addExpectationView) {
        this.addExpectationView = addExpectationView;
    }

    public void setAddTaskView(HabitContract.AddTaskView addTaskView) {
        this.addTaskView = addTaskView;
    }

    public void setNewHabitView(HabitContract.NewHabitView newHabitView) {
        this.newHabitView = newHabitView;
    }

    @Override
    public void saveNewTempTask() {

        model.saveNewTempTask(new TempTask(addTaskView.getNewTaskName()));
        addTaskView.finishActivity();
    }

    @Override
    public void goAddTask() {

        newHabitView.goAddTask();
    }

    @Override
    public void saveNewTempExpectation() {

        model.saveNewTempExpectation(new TempExpectation(addExpectationView.getNewExpectationName()));
        addExpectationView.finishActivity();
    }

    @Override
    public void goAddExpectation() {

        newHabitView.goAddExpectation();
    }

    @Override
    public void saveNewHabit() {

        model.saveNewHabit(newHabitView.getHabitName());

        model.displayHabitTasks();

        model.displayHabitExpectations();

        newHabitView.finishActivity();
    }

    @Override
    public void start() {

    }
}
