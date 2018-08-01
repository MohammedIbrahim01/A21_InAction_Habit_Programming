package com.InAction.X.x21InAction.expectation;

import android.support.v7.widget.RecyclerView;

import com.InAction.X.x21InAction.BasePresenter;
import com.InAction.X.x21InAction.expectation.model.Expectation;
import com.InAction.X.x21InAction.expectation.view.ExpectationAdapter;

import java.util.List;

public interface ExpectationContract {

    interface Model{

        void insertExpectation(Expectation expectation);

        void deleteExpectation(Expectation expectation);

        void insertExpectationList(List<Expectation> expectationListFromHabit);
    }

    interface View {

        void setupRecyclerViewWithAdapter();

        void setExpectations(List<Expectation> expectationList);
    }

    interface Presenter extends BasePresenter {

        void setupRecyclerViewWithAdapter();

        void setupExpectationLive();

        void deleteExpectation(Expectation expectation);
    }
}
