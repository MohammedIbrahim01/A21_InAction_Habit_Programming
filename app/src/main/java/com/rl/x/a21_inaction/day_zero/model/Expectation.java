package com.rl.x.a21_inaction.day_zero.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "expectations")
public class Expectation {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    public Expectation(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public Expectation(String name) {
        this.name = name;
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
