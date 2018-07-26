package com.rl.x.a21_inaction.habit.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.rl.x.a21_inaction.tasks.model.Task;

import java.util.List;

@Entity(tableName = "habits")
public class Habit {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String habitName;

    private List<Task> taskList;

    public Habit(int id, String habitName, List<Task> taskList) {
        this.id = id;
        this.habitName = habitName;
        this.taskList = taskList;
    }

    @Ignore
    public Habit(String habitName, List<Task> taskList) {
        this.habitName = habitName;
        this.taskList = taskList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
