package com.rl.x.a21_inaction.expectation.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "expectations")
public class Expectation {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String habitName;


    public Expectation(int id, String name, String habitName) {

        this.id = id;
        this.name = name;
        this.habitName = habitName;
    }


    @Ignore
    public Expectation(String name, String habitName) {

        this.name = name;
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
}
