package com.InAction.X.x21InAction.utils.converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.InAction.X.x21InAction.expectation.model.Expectation;

import java.lang.reflect.Type;
import java.util.List;

public class ExpectationsListConverter {

    @TypeConverter
    public static String expectationListToJson(List<Expectation> expectationList) {

        return new Gson().toJson(expectationList);
    }

    @TypeConverter
    public static List<Expectation> JsonToExpectationList(String json) {

        Type type = new TypeToken<List<Expectation>>(){}.getType();

        return new Gson().fromJson(json, type);
    }
}
