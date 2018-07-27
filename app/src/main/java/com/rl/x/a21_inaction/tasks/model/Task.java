package com.rl.x.a21_inaction.tasks.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Calendar;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    @TypeConverters(CalendarConverter.class)
    private Calendar calendar;

    private String habitName;


    public Task(int id, String name, Calendar calendar, String habitName) {

        this.id = id;
        this.name = name;
        this.calendar = calendar;
        this.habitName = habitName;
    }

    @Ignore
    public Task(String name, Calendar calendar, String habitName) {

        this.name = name;
        this.calendar = calendar;
        this.habitName = habitName;
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

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }
}
