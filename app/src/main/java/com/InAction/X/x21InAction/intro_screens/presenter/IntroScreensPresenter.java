package com.InAction.X.x21InAction.intro_screens.presenter;

import com.InAction.X.x21InAction.intro_screens.model.IntroFragments;
import com.InAction.X.x21InAction.intro_screens.model.IntroScreensModel;
import com.InAction.X.x21InAction.intro_screens.view.IntoScreensAdapter;
import com.InAction.X.x21InAction.utils.CustomViewPager;

public class IntroScreensPresenter {

    public void setupAdapterWithViewPager(IntoScreensAdapter adapter, CustomViewPager viewPager){

        //greeting
        adapter.addFragment(IntroScreensModel.getScreen(0));
        adapter.addFragment(IntroScreensModel.getScreen(1));
        adapter.addFragment(IntroScreensModel.getScreen(2));
        adapter.addFragment(IntroScreensModel.getScreen(3));
        //getting info
        adapter.addFragment(IntroScreensModel.getScreen(4));
        adapter.addFragment(IntroScreensModel.getScreen(5));
        adapter.addFragment(IntroScreensModel.getScreen(6));
        adapter.addFragment(IntroScreensModel.getScreen(7));
        adapter.addFragment(IntroScreensModel.getScreen(8));
        adapter.addFragment(IntroScreensModel.getScreen(9));
        adapter.addFragment(IntroScreensModel.getScreen(10));

        viewPager.setAdapter(adapter);
    }
}
