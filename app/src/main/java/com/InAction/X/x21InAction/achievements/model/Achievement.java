package com.InAction.X.x21InAction.achievements.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "achievements")
public class Achievement {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private int day;


    public Achievement(int id, String name, int day) {

        this.id = id;
        this.name = name;
        this.day = day;
    }

    @Ignore
    public Achievement(String name, int day) {

        this.name = name;
        this.day = day;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
