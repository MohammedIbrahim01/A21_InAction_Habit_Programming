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
    private HabitDao habitDao;

    private TaskDao taskDao;
    private ExpectationDao expectationDao;

    public HabitModel(Context applicationContext) {

        diskIOExecutor = AppExecutors.getInstance().getDiskIO();
        tempTaskDao = AppDatabase.getInstance(applicationContext).tempTaskDao();
        tempExpectationDao = AppDatabase.getInstance(applicationContext).tempExpectationDao();
        habitDao = AppDatabase.getInstance(applicationContext).habitDao();

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
    public void saveNewHabit(String name) {

        ArrayList<Task> taskList = getTasksFromTempTasks();
        ArrayList<Expectation> expectationList = getExpectationsFromTempExpectations();

        final Habit habit = new Habit(name, taskList, expectationList);
        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                habitDao.insertHabit(habit);
            }
        });
    }

    @Override
    public void displayHabitTasks() {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                taskDao.insertAllTask(habitDao.getHabit("nofap").getTaskList());
            }
        });
    }

    @Override
    public void displayHabitExpectations() {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                expectationDao.insertAllExpectation(habitDao.getHabit("nofap").getExpectationList());
            }
        });
    }


    private List<TempTask> tempTaskList = new ArrayList<>();
    private Boolean haveTempTasks;

    @Override
    public List<TempTask> getAllTempTasksThenDeleteTempTasksFromDatabase() {

        haveTempTasks = false;
        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempTaskList = tempTaskDao.getAllTempTasks();
                haveTempTasks = true;
            }
        });

        while (!haveTempTasks) ;

        deleteTempTasks();

        return tempTaskList;
    }


    private List<TempExpectation> tempExpectationList = new ArrayList<>();
    private Boolean haveTempExpectations;

    @Override
    public List<TempExpectation> getAllTempExpectationsThenDeleteTempExpectationsFromDatabase() {

        haveTempExpectations = false;
        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempExpectationList = tempExpectationDao.getAllTempExpectations();
                haveTempExpectations = true;
            }
        });

        while (!haveTempExpectations) ;

        deleteTempExpectations();

        return tempExpectationList;
    }


    @Override
    public ArrayList<Task> getTasksFromTempTasks() {

        ArrayList<Task> taskList = new ArrayList<>();
        List<TempTask> tempTasks = getAllTempTasksThenDeleteTempTasksFromDatabase();

        for (TempTask tempTask : tempTasks) {

            taskList.add(new Task(tempTask.getName()));
        }
        return taskList;
    }

    @Override
    public ArrayList<Expectation> getExpectationsFromTempExpectations() {

        ArrayList<Expectation> expectationList = new ArrayList<>();
        List<TempExpectation> tempExpectations = getAllTempExpectationsThenDeleteTempExpectationsFromDatabase();

        for (TempExpectation tempExpectation : tempExpectations) {

            expectationList.add(new Expectation(tempExpectation.getName()));
        }
        return expectationList;
    }

//    @Override
//    public void saveTempTasksInRealTaskDatabase() {
//
//        List<TempTask> tempTasks = getAllTempTasksThenDeleteTempTasksFromDatabase();
//
//        for (final TempTask tempTask : tempTasks) {
//
//            diskIOExecutor.execute(new Runnable() {
//                @Override
//                public void run() {
//
//                    taskDao.insertTask(new Task(tempTask.getName()));
//                }
//            });
//        }
//    }

//    @Override
//    public void saveTempExpectationsInRealExpectationDatabase() {
//
//        List<TempExpectation> tempExpectations = getAllTempExpectationsThenDeleteTempExpectationsFromDatabase();
//
//        for (final TempExpectation tempExpectation : tempExpectations) {
//
//            diskIOExecutor.execute(new Runnable() {
//                @Override
//                public void run() {
//
//                    expectationDao.insertExpectation(new Expectation(tempExpectation.getName()));
//                }
//            });
//        }
//    }

    @Override
    public void deleteTempTasks() {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempTaskDao.deleteAllTempTasks();
            }
        });
    }

    @Override
    public void deleteTempExpectations() {

        diskIOExecutor.execute(new Runnable() {
            @Override
            public void run() {

                tempExpectationDao.deleteAllTempExpectations();
            }
        });
    }
}
