package com.rl.x.a21_inaction.habit.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.rl.x.a21_inaction.day_zero.model.Expectation;
import com.rl.x.a21_inaction.tasks.model.Task;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "habits")
public class Habit {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    @TypeConverters(TasksListConverter.class)
    private List<Task> taskList;

    @TypeConverters({ExpectationsListConverter.class})
    private List<Expectation> expectationList;

    public Habit(int id, String name, List<Task> taskList, List<Expectation> expectationList) {
        this.id = id;
        this.name = name;
        this.taskList = taskList;
        this.expectationList = expectationList;
    }

    @Ignore
    public Habit(String name, List<Task> taskList, List<Expectation> expectationList) {
        this.name = name;
        this.taskList = taskList;
        this.expectationList = expectationList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Expectation> getExpectationList() {
        return expectationList;
    }

    public void setExpectationList(List<Expectation> expectationList) {
        this.expectationList = expectationList;
    }
}
