package com.rl.x.a21_inaction.expectation.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rl.x.a21_inaction.R;
import com.rl.x.a21_inaction.expectation.model.Expectation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpectationAdapter extends RecyclerView.Adapter<ExpectationAdapter.ViewHolder> {

    private List<Expectation> expectationList = new ArrayList<>();

    public List<Expectation> getExpectationList() {
        return expectationList;
    }

    public void setExpectationList(List<Expectation> expectationList) {

        this.expectationList = expectationList;
    }

    @NonNull
    @Override
    public ExpectationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expectation_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpectationAdapter.ViewHolder holder, int position) {

        Expectation currentExpectation = expectationList.get(position);
        holder.nameTextView.setText(currentExpectation.getName());
    }

    @Override
    public int getItemCount() {

        return expectationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.expectation_row_name_textView)
        TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
