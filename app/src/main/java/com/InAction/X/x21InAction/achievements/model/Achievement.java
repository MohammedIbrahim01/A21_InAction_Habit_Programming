package com.InAction.X.x21InAction.achievements.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "achievements")
public class Achievement {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    public Achievement(int id, String name) {

        this.id = id;
        this.name = name;
    }

    @Ignore
    public Achievement(String name) {

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
