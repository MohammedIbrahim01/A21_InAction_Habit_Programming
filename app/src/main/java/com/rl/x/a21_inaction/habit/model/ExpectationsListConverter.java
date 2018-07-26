package com.rl.x.a21_inaction.habit.model;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rl.x.a21_inaction.tasks.model.Task;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExpectationsListConverter {

    @TypeConverter
    public static String expectationListToJson(List<Exception> exceptionList) {

        return new Gson().toJson(exceptionList);
    }

    @TypeConverter
    public static List<Exception> JsonToExpectationList(String json) {

        Type type = new TypeToken<ArrayList<Task>>(){}.getType();

        return new Gson().fromJson(json, type);
    }
}
