package com.rl.x.a21_inaction._main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

public interface MainContract {

    interface View{

    }

    interface Presenter{

        void setupTabLayoutAndViewPager(ViewPager viewPager, TabLayout tabLayout);
    }
}
