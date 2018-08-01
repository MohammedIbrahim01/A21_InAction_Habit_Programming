package com.InAction.X.x21InAction.add_expectation;

import com.InAction.X.x21InAction.add_expectation.model.TempExpectation;

import java.util.List;

public interface AddExpectationContract {

    interface Model {

        void insertTempExpectation(TempExpectation tempExpectation);

        List<TempExpectation> getTempExpectationList();
    }

    interface View {

        String getExpectationName();

        void finishActivity();

        String getHabitName();
    }

    interface Presenter {

        void insertTempExpectation();
    }
}
