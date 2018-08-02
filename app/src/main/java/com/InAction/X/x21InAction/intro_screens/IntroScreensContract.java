package com.InAction.X.x21InAction.intro_screens;


import com.InAction.X.x21InAction.intro_screens.view.IntoScreensAdapter;
import com.InAction.X.x21InAction.utils.CustomViewPager;

public interface IntroScreensContract {

    interface View {

        IntoScreensAdapter getAdapter();

        CustomViewPager getViewPager();

        int getViewPagerCurrentItem();

        void setButtonsVisibilities(int page);
    }

    interface Presenter {

        void setupAdapterWithViewPager();

        void nextPage();

        void previousPage();
    }

    interface Communications {


    }
}
