package com.InAction.X.x21InAction.intro_screens.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.habit.model.Habit;
import com.InAction.X.x21InAction.habit.model.HabitModel;
import com.InAction.X.x21InAction.intro_screens.presenter.IntroScreensPresenter;
import com.InAction.X.x21InAction.manager.AppManager;
import com.InAction.X.x21InAction.utils.CustomViewPager;
import com.InAction.X.x21InAction.intro_screens.model.IntroFragments;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroScreensActivity extends AppCompatActivity implements View.OnClickListener {


    public static final int PAGE_CONTAINS_HABIT_NAME = 5;
    public static final String NAME_SHARED_PREFERENCES = "name-sharedPreferences";
    public static final String KEY_FIRST_LAUNCH = "key-first_launch";

    private HabitModel habitModel;
    private AppManager manager;

    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.start)
    TextView start;
    @BindView(R.id.intro_screens_viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.root_intro)
    LinearLayout rootIntro;

    private IntoScreensAdapter adapter;

    public String habitName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);

        habitModel = new HabitModel(getApplicationContext());
        manager = new AppManager(getApplicationContext(), this);

        adapter = new IntoScreensAdapter(getSupportFragmentManager());

        IntroScreensPresenter presenter = new IntroScreensPresenter();

        presenter.setupAdapterWithViewPager(adapter, viewPager);
    }


    @Override
    protected void onResume() {
        super.onResume();

        back.setOnClickListener(this);
        next.setOnClickListener(this);
        start.setOnClickListener(this);

        setBackgroundAndButtonsVisibilities(viewPager.getCurrentItem());
    }


    @Override
    public void onClick(View view) {

        int page = viewPager.getCurrentItem();

        switch (view.getId()) {

            case R.id.next:
                checkAndGetHabitName(page);
                viewPager.setCurrentItem(++page);
                setBackgroundAndButtonsVisibilities(page);
                Toast.makeText(IntroScreensActivity.this, "next", Toast.LENGTH_SHORT).show();
                break;

            case R.id.back:
                viewPager.setCurrentItem((--page), true);
                setBackgroundAndButtonsVisibilities(page);
                Toast.makeText(IntroScreensActivity.this, "back", Toast.LENGTH_SHORT).show();
                break;

            case R.id.start:
                if (page == 10) {
                    Habit habit = new Habit(habitName, manager.getTaskListFromTemp(), manager.getExpectationListFromTemp());
                    habitModel.insertHabit(habit);
                    manager.startHabitPrograming();
                    getSharedPreferences(NAME_SHARED_PREFERENCES, MODE_PRIVATE).edit().putBoolean(KEY_FIRST_LAUNCH, false).apply();
                    finish();
                    break;
                }
                viewPager.setCurrentItem(++page);
                setBackgroundAndButtonsVisibilities(page);
                Toast.makeText(IntroScreensActivity.this, "start", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void checkAndGetHabitName(int page) {

        if (page == PAGE_CONTAINS_HABIT_NAME) {

            //get current fragment through adapter and cat it to IntroFragments then get habit name from it
            habitName = ((IntroFragments) adapter.getFragment(viewPager.getCurrentItem())).guideHabitNameEditText.getText().toString();
        }
    }


    private void setBackgroundAndButtonsVisibilities(int page) {

        /**
         * background color
         */
        if (page <= 3)
            rootIntro.setBackgroundColor(getResources().getColor(R.color.colorIntroBackground1));
        else
            rootIntro.setBackgroundColor(getResources().getColor(R.color.colorIntroBackground2));


        /**
         * buttons Visibilities
         * ( Next _ Back _ Start )
         */
        switch (page) {

            case 0:
            case 4:
                back.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
                break;
            case 3:
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                start.setVisibility(View.VISIBLE);
                break;
            case 10:
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                start.setVisibility(View.VISIBLE);
                start.setText("Finish");
                break;
            default:
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
        }
    }

}