package com.rl.x.a21_inaction.intro_screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rl.x.a21_inaction.R;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomViewPager viewPager;
    private IntoAdapter adapter;

    private TextView back;
    private TextView next;
    private TextView start;
    private LinearLayout rootIntro;

    public String habitName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        back = (TextView) findViewById(R.id.back);
        next = (TextView) findViewById(R.id.next);
        start = (TextView) findViewById(R.id.start);
        rootIntro = (LinearLayout) findViewById(R.id.root_intro);

        viewPager = (CustomViewPager) findViewById(R.id.fragment_container);

        adapter = new IntoAdapter(getSupportFragmentManager());
        adapter.addFragment(IntroFragments.getIntro(0));
        adapter.addFragment(IntroFragments.getIntro(1));
        adapter.addFragment(IntroFragments.getIntro(2));
        adapter.addFragment(IntroFragments.getIntro(3));
        adapter.addFragment(IntroFragments.getIntro(4));
        adapter.addFragment(IntroFragments.getIntro(5));
        adapter.addFragment(IntroFragments.getIntro(6));
        adapter.addFragment(IntroFragments.getIntro(7));
        adapter.addFragment(IntroFragments.getIntro(8));
        adapter.addFragment(IntroFragments.getIntro(9));
        viewPager.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

        back.setOnClickListener(this);
        next.setOnClickListener(this);
        start.setOnClickListener(this);

        setVisibilities(viewPager.getCurrentItem());
        setBackgroundColor(viewPager.getCurrentItem());
    }


    @Override
    public void onClick(View view) {

        int page = viewPager.getCurrentItem();

        switch (view.getId()) {

            case R.id.next:
                checkAndGetHabitName(page);
                viewPager.setCurrentItem(++page);
                setVisibilities(page);
                setBackgroundColor(viewPager.getCurrentItem());
                Toast.makeText(IntroActivity.this, "next", Toast.LENGTH_SHORT).show();
                break;

            case R.id.back:
                viewPager.setCurrentItem((--page), true);
                setVisibilities(page);
                setBackgroundColor(viewPager.getCurrentItem());
                Toast.makeText(IntroActivity.this, "back", Toast.LENGTH_SHORT).show();
                break;

            case R.id.start:
                if (page == 9) {
                    //go to habit overview
                    Toast.makeText(IntroActivity.this, "go to habit over view", Toast.LENGTH_SHORT).show();
                    break;
                }
                viewPager.setCurrentItem(++page);
                setVisibilities(page);
                setBackgroundColor(viewPager.getCurrentItem());
                Toast.makeText(IntroActivity.this, "start", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void checkAndGetHabitName(int page) {

        if (page == 5) {

            habitName = new IntroFragments().guideHabitNameEditText.getText().toString();
        }
    }


    private void setBackgroundColor(int page) {

        if (page <= 3)
            rootIntro.setBackgroundColor(getResources().getColor(R.color.colorIntroBackground1));
        else
            rootIntro.setBackgroundColor(getResources().getColor(R.color.colorIntroBackground2));

    }

    private void setVisibilities(int page) {

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
            case 9:
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