package com.rl.x.a21_inaction.habit.model;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rl.x.a21_inaction.tasks.model.Task;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TasksListConverter {

    @TypeConverter
    public static String taskListToJson(List<Task> taskList) {

        return new Gson().toJson(taskList);
    }

    @TypeConverter
    public static List<Task> JsonToTaskList(String json) {

        Type type = new TypeToken<List<Task>>(){}.getType();

        return new Gson().fromJson(json, type);
    }
}
