package com.rl.x.a21_inaction._main.view;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.rl.x.a21_inaction.R;
import com.rl.x.a21_inaction._main.MainContract;
import com.rl.x.a21_inaction._main.preseter.MainPresenter;
import com.rl.x.a21_inaction.database.AppDatabase;
import com.rl.x.a21_inaction.habit.view.NewHabitActivity;
import com.rl.x.a21_inaction.utils.AppExecutors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter presenter;

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

//        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
//            @Override
//            public void run() {
//                AppDatabase.getInstance(getApplicationContext()).clearAllTables();
//            }
//        });

        presenter = new MainPresenter(this);

        presenter.setupTabLayoutAndViewPager();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_habit :
                presenter.goAddHabit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setupTabLayoutAndViewPager(AppFragmentPagerAdapter adapter) {
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public FragmentManager getAppSupportFragmentManager() {
        return getSupportFragmentManager();
    }

    @Override
    public void goAddHabit() {
        startActivity(new Intent(MainActivity.this, NewHabitActivity.class));
    }

}
