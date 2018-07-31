package com.rl.x.a21_inaction.intro_screens;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.rl.x.a21_inaction.R;
import com.rl.x.a21_inaction.manager.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class IntroFragments extends Fragment {

    public static final String KEY_PAGE = "key-page";

    private AppManager manager;

    private int page;

    @Nullable
    @BindView(R.id.guide_habitName)
    EditText guideHabitNameEditText;

    @Optional
    @OnClick(R.id.guidance_add_expectation_button)
    void addExpectation() {

        manager.goAddExpectation(((IntroActivity) getActivity()).habitName);
        Toast.makeText(getContext(), "add expectation", Toast.LENGTH_SHORT).show();
    }

    @Optional
    @OnClick(R.id.guidance_add_task_button)
    void addTask() {

        manager.goAddTask(((IntroActivity) getActivity()).habitName);
        Toast.makeText(getContext(), "add task", Toast.LENGTH_SHORT).show();
    }

    public IntroFragments() {
    }

    public static IntroFragments getIntro(int page) {

        Bundle args = new Bundle();
        args.putInt(KEY_PAGE, page);

        IntroFragments fragment = new IntroFragments();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(KEY_PAGE)) {

            page = getArguments().getInt(KEY_PAGE);
        }
        manager = new AppManager(getActivity().getApplicationContext(), getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int layout;

        switch (page) {

            case 0:
                layout = R.layout.intro_0;
                break;
            case 1:
                layout = R.layout.intro_1;
                break;
            case 2:
                layout = R.layout.intro_2;
                break;
            case 3:
                layout = R.layout.intro_3;
                break;
            case 4:
                layout = R.layout.guide_0;
                break;
            case 5:
                layout = R.layout.guide_1;
                break;
            case 6:
                layout = R.layout.guide_2;
                break;
            case 7:
                layout = R.layout.guide_3;
                break;
            case 8:
                layout = R.layout.guide_4;
                break;
            case 9:
                layout = R.layout.guide_5;
                break;

            default:
                layout = -1;
        }

        View view = inflater.inflate(layout, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
