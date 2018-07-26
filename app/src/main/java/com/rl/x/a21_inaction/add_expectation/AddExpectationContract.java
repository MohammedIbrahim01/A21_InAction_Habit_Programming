package com.rl.x.a21_inaction.add_expectation;

import com.rl.x.a21_inaction.add_expectation.model.TempExpectation;

import java.util.List;

public interface AddExpectationContract {

    interface Model {

        void insertTempExpectation(TempExpectation tempExpectation);

        List<TempExpectation> getTempExpectationList();
    }

    interface View {

        String getNewExpectationName();

        void finishActivity();
    }

    interface Presenter {

        void insertTempExpectation();
    }
}
