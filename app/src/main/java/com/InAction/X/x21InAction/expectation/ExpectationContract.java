package com.InAction.X.x21InAction.expectation;

import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.widget.RecyclerView;

import com.InAction.X.x21InAction.BasePresenter;
import com.InAction.X.x21InAction.expectation.model.Expectation;
import com.InAction.X.x21InAction.expectation.view.ExpectationAdapter;
import com.InAction.X.x21InAction.expectation.view.ExpectationViewModel;

import java.util.List;

public interface ExpectationContract {

    interface Model {

        void insertExpectationList(List<Expectation> expectationListFromHabit);
    }

    interface View {

        ExpectationAdapter getAdapter();

        RecyclerView getRecyclerView();

        LifecycleOwner getLifeCycleOwner();

        ExpectationViewModel getViewModel();

        void setExpectations(List<Expectation> expectationList);
    }

    interface Presenter {

        void setupRecyclerViewWithAdapter();

        void setupExpectationLive();

        void insertExpectationList(List<Expectation> expectationListFromHabit);
    }

    interface Communication {

        void insertExpectationList(List<Expectation> expectationListFromHabit);
    }
}
