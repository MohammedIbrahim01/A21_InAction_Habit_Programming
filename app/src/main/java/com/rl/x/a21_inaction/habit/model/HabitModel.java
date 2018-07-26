package com.rl.x.a21_inaction.habit.model;

import android.content.Context;

import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.day_zero.model.Expectation;
import com.rl.x.a21_inaction.day_zero.model.ExpectationDao;
import com.rl.x.a21_inaction.habit.HabitContract;
import com.rl.x.a21_inaction.tasks.model.Task;
import com.rl.x.a21_inaction.tasks.model.TaskDao;
import com.rl.x.a21_inaction.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class HabitModel implements HabitContract.Model {

    private Executor diskIOExecutor;
    private TempTaskDao tempTaskDao;
    private TempExpectationDao tempExpectationDao;

    private TaskDao taskDao;
    private ExpectationDao expectationDao;

    public HabitModel(Context applicationContext) {

        diskIOExecutor = AppExecutors.getInstance().getDiskIO();
        tempTaskDao = AppDatabase.getInstance(applicationContext).tempTaskDao();
        tempExpectationDao = AppDatabase.getInstance(applicationContext).tempExpectationDao();

        taskDao = AppDatabase.getInstance(applicationContext).taskDao();
        expectationDao = AppDatabase.getInstance(applicationContext).expectationDao();
    }

    @Override
    public void saveNewTempTask(final TempTask tempTask) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempTaskDao.insertTempTask(tempTask);
            }
        });
    }

    @Override
    public void saveNewTempExpectation(final TempExpectation tempExpectation) {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempExpectationDao.insertTempExpectation(tempExpectation);
            }
        });
    }

    @Override
    public void saveNewHabit(Habit habit) {

    }


    private List<TempTask> tempTaskList = new ArrayList<>();
    private Boolean haveTempTasks;

    @Override
    public List<TempTask> getAllTempTasks() {

        haveTempTasks = false;
        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempTaskList = tempTaskDao.getAllTempTasks();
                haveTempTasks = true;
            }
        });

        while (!haveTempTasks) ;

        return tempTaskList;
    }


    private List<TempExpectation> tempExpectationList = new ArrayList<>();
    private Boolean haveTempExpectations;

    @Override
    public List<TempExpectation> getAllTempExpectations() {

        haveTempExpectations = false;
        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempExpectationList = tempExpectationDao.getAllTempExpectations();
                haveTempExpectations = true;
            }
        });

        while (!haveTempExpectations) ;

        return tempExpectationList;
    }

    @Override
    public void saveTempTasksInRealTaskDatabase() {

        List<TempTask> tempTasks = getAllTempTasks();

        for (final TempTask tempTask : tempTasks) {

            diskIOExecutor.execute(new Runnable() {
                @Override
                public void run() {

                    taskDao.insertTask(new Task(tempTask.getName()));
                }
            });
        }
    }

    @Override
    public void saveTempExpectationsInRealExpectationDatabase() {

        List<TempExpectation> tempExpectations = getAllTempExpectations();

        for (final TempExpectation tempExpectation : tempExpectations) {

            diskIOExecutor.execute(new Runnable() {
                @Override
                public void run() {

                    expectationDao.insertExpectation(new Expectation(tempExpectation.getName()));
                }
            });
        }
    }

    @Override
    public void removeTempTasks() {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempTaskDao.deleteAllTempTasks();
            }
        });
    }

    @Override
    public void removeTempExpectations() {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempExpectationDao.deleteAllTempExpectations();
            }
        });
    }
}
