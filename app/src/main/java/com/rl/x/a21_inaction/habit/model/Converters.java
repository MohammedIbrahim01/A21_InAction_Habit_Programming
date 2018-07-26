package com.rl.x.a21_inaction.habit.model;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rl.x.a21_inaction.tasks.model.Task;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {

    @TypeConverter
    public static String toJson(List<Task> taskList) {

        return new Gson().toJson(taskList);
    }

    @TypeConverter
    public static List<Task> toTaskList(String json) {

        Type type = new TypeToken<ArrayList<Task>>(){}.getType();

        return new Gson().fromJson(json, type);
    }
}
