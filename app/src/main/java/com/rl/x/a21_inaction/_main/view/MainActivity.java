package com.rl.x.a21_inaction._main.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rl.x.a21_inaction.R;
import com.rl.x.a21_inaction._main.preseter.MainPresenter;
import com.rl.x.a21_inaction.achievements.view.AchievementsFragment;
import com.rl.x.a21_inaction.day_zero.view.DayZeroFragment;
import com.rl.x.a21_inaction.tasks.view.TasksFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.counter_textView)
    TextView counterTextView;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MainPresenter presenter = new MainPresenter(this);

        presenter.setupTabLayoutAndViewPager(viewPager, tabLayout);

    }

}
