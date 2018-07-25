package com.rl.x.a21_inaction.habit.presenter;

import com.rl.x.a21_inaction.habit.HabitContract;
import com.rl.x.a21_inaction.tasks.model.Task;

public class HabitPresenter implements HabitContract.Presenter {

    private HabitContract.AddTaskView addTaskView;
    private HabitContract.NewHabitView newHabitView;
    private HabitContract.AddExpectationView addExpectationView;

    public HabitPresenter() {
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
    public void saveNewTaskTemp() {

        String taskName = addTaskView.getNewTaskName();
        addTaskView.finishActivity();
    }

    @Override
    public void goAddTask() {

        newHabitView.goAddTask();
    }

    @Override
    public void saveNewExpectationTemp() {

        String expectationName = addExpectationView.getNewExpectationName();
        addExpectationView.finishActivity();
    }

    @Override
    public void goAddExpectation() {

        newHabitView.goAddExpectation();
    }

    @Override
    public void saveNewHabit() {

        newHabitView.finishActivity();
    }

    @Override
    public void start() {

    }
}
