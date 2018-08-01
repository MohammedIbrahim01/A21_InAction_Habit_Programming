package com.InAction.X.x21InAction.add_expectation.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction.add_expectation.model.TempExpectation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TempExpectationsAdapter extends RecyclerView.Adapter<TempExpectationsAdapter.ViewHolder> {

    private List<TempExpectation> tempExpectationList = new ArrayList<>();

    public void setTempExpectationList(List<TempExpectation> tempExpectationList) {
        this.tempExpectationList = tempExpectationList;
    }

    @NonNull
    @Override
    public TempExpectationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expectation_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TempExpectationsAdapter.ViewHolder holder, int position) {

        holder.expectationNameTextView.setText(tempExpectationList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return tempExpectationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.expectation_row_name_textView)
        TextView expectationNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
