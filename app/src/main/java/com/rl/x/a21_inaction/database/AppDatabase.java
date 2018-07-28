package com.rl.x.a21_inaction.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.rl.x.a21_inaction.achievements.model.Achievement;
import com.rl.x.a21_inaction.achievements.model.AchievementDao;
import com.rl.x.a21_inaction.expectation.model.Expectation;
import com.rl.x.a21_inaction.expectation.model.ExpectationDao;
import com.rl.x.a21_inaction.habit.model.Habit;
import com.rl.x.a21_inaction.habit.model.HabitDao;
import com.rl.x.a21_inaction.add_expectation.model.TempExpectation;
import com.rl.x.a21_inaction.add_expectation.model.TempExpectationDao;
import com.rl.x.a21_inaction.add_task.model.TempTask;
import com.rl.x.a21_inaction.add_task.model.TempTaskDao;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.model.TaskDao;

@Database(entities = {Task.class, Achievement.class, Expectation.class, TempTask.class, TempExpectation.class, Habit.class}, version = 13, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    public static final String NAME_DATABASE = "name-database";
    private static AppDatabase sInstance;


    public static AppDatabase getInstance(Context applicationContext) {

        if (sInstance == null) {

            synchronized (LOCK) {

                sInstance = Room.databaseBuilder(applicationContext, AppDatabase.class, NAME_DATABASE)
                        .fallbackToDestructiveMigration()
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
