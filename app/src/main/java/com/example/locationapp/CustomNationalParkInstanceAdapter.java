package com.example.locationapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomNationalParkInstanceAdapter extends RecyclerView.Adapter<CustomNationalParkInstanceAdapter.ViewHolder> {
    private List<NationalParkInstanceInitializer> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final CheckBox hasCompletedButton;
        private final CheckBox hasVistedButton;

        public ViewHolder(View itemView) {
            super(itemView);
            //define click listener for view holder
            textView = itemView.findViewById(R.id.textView);
            hasCompletedButton = itemView.findViewById(R.id.checkbox_has_completed);
            hasVistedButton = (CheckBox) itemView.findViewById(R.id.checkbox_has_visited);
        }
        public TextView getTextView() {
            return textView;
        }
        public CheckBox getHasCompletedButton() {
            return hasCompletedButton;
        }
        public CheckBox getHasVisitedButton() {
            return hasVistedButton;
        }
    }

    //initialize dataset
    public CustomNationalParkInstanceAdapter(List<NationalParkInstanceInitializer> dataSet) {
        localDataSet = dataSet;
    }

    @Override
    public CustomNationalParkInstanceAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //create a new view which defines the ui of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.np_instance_initializer_row, viewGroup, false);
        return new CustomNationalParkInstanceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CustomNationalParkInstanceAdapter.ViewHolder viewHolder, final int position) {
        //get element from dataset this position and replace with contents of new element
        viewHolder.getTextView().setText(localDataSet.get(position).getParkName());

        viewHolder.getHasCompletedButton().setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // CheckBox is checked
                localDataSet.get(position).setHasCompleted(true);
            } else {
                // CheckBox is unchecked
                localDataSet.get(position).setHasCompleted(false);
            }
        });

        viewHolder.getHasVisitedButton().setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) (buttonView, isChecked) -> {
            if (isChecked) {
                // CheckBox is checked
                localDataSet.get(position).setHasVisited(true);
            } else {
                // CheckBox is unchecked
                localDataSet.get(position).setHasVisited(false);
            }
        });



    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public List<NationalParkInstanceInitializer> getLocalDataSet() {
        return localDataSet;
    }
}
