package com.InAction.X.x21InAction.intro_screens.model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.manager.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroFragments extends Fragment {

    public static final String KEY_PAGE = "key-page";

    private AppManager manager;

    private int page;

    @Nullable
    @BindView(R.id.guide_habitName)
    public
    EditText guideHabitNameEditText;


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
            case 10:
                layout = R.layout.habit_over_view;
                break;

            default:
                layout = -1;
        }

        View view = inflater.inflate(layout, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
