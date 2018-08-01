package com.InAction.X.x21InAction.temp_task.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.InAction.X.x21InAction.utils.converters.CalendarConverter;

import java.util.Calendar;

@Entity(tableName = "tempTasks")
public class TempTask {


    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String habitName;

    @TypeConverters(CalendarConverter.class)
    private Calendar calendar;


    public TempTask(int id, String name, Calendar calendar, String habitName) {

        this.id = id;
        this.name = name;
        this.calendar = calendar;
        this.habitName = habitName;
    }


    @Ignore
    public TempTask(String name, Calendar calendar, String habitName) {

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

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
