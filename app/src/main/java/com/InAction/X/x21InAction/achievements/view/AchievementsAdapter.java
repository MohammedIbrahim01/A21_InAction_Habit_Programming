package com.InAction.X.x21InAction.achievements.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.achievements.model.Achievement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AchievementsAdapter extends RecyclerView.Adapter<AchievementsAdapter.ViewHolder> {

    private List<Achievement> achievementList = new ArrayList<>();


    public List<Achievement> getAchievementList() {
        return achievementList;
    }


    public void setAchievementList(List<Achievement> achievementList) {

        this.achievementList = achievementList;
    }


    @NonNull
    @Override
    public AchievementsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AchievementsAdapter.ViewHolder holder, int position) {

        Achievement currentAchievement = achievementList.get(position);

        holder.nameTextView.setText(currentAchievement.getName());
    }


    @Override
    public int getItemCount() {

        return achievementList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.achievement_row_name_textView)
        TextView nameTextView;
        @BindView(R.id.achievement_row_day_textView)
        TextView dayTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
