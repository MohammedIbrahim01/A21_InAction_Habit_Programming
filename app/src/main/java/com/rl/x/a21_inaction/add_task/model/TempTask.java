package com.rl.x.a21_inaction.add_task.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.rl.x.a21_inaction.tasks.model.CalendarConverter;

import java.util.Calendar;

@Entity(tableName = "tempTasks")
public class TempTask {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    @TypeConverters(CalendarConverter.class)
    private Calendar calendar;

    public TempTask(int id, String name, Calendar calendar) {

        this.id = id;
        this.name = name;
        this.calendar = calendar;
    }

    @Ignore
    public TempTask(String name, Calendar calendar) {

        this.name = name;
        this.calendar = calendar;
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
}
