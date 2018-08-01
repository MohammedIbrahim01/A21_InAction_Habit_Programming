package com.InAction.X.x21InAction.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.InAction.X.x21InAction.achievements.model.Achievement;
import com.InAction.X.x21InAction.achievements.model.AchievementDao;
import com.InAction.X.x21InAction.expectation.model.Expectation;
import com.InAction.X.x21InAction.expectation.model.ExpectationDao;
import com.InAction.X.x21InAction.habit.model.Habit;
import com.InAction.X.x21InAction.habit.model.HabitDao;
import com.InAction.X.x21InAction.add_expectation.model.TempExpectation;
import com.InAction.X.x21InAction.add_expectation.model.TempExpectationDao;
import com.InAction.X.x21InAction.add_task.model.TempTask;
import com.InAction.X.x21InAction.add_task.model.TempTaskDao;
import com.InAction.X.x21InAction.tasks.model.Task;
import com.InAction.X.x21InAction.tasks.model.TaskDao;

@Database(entities = {Task.class, Achievement.class, Expectation.class, TempTask.class, TempExpectation.class, Habit.class}, version = 14, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    public static final String NAME_DATABASE = "name-database";
    private static AppDatabase sInstance;


    public static AppDatabase getInstance(Context applicationContext) {

        if (sInstance == null) {

            synchronized (LOCK) {

                sInstance = Room.databaseBuilder(applicationContext, AppDatabase.class, NAME_DATABASE)
                        .build();
            }
        }
        return sInstance;
    }


    public abstract TaskDao taskDao();

    public abstract AchievementDao achievementDao();

    public abstract ExpectationDao expectationDao();

    public abstract TempTaskDao tempTaskDao();

    public abstract TempExpectationDao tempExpectationDao();

    public abstract HabitDao habitDao();
}
