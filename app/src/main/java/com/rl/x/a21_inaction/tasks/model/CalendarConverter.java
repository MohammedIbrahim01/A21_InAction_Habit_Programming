package com.rl.x.a21_inaction.tasks.model;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

import java.util.Calendar;

public class CalendarConverter {

    @TypeConverter
    public static String calendarToJson(Calendar calendar) {

        return new Gson().toJson(calendar);
    }

    @TypeConverter
    public static Calendar jsonToCalendar(String json) {

        return new Gson().fromJson(json, Calendar.class);
    }
}
