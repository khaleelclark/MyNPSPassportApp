package com.example.locationapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomNationalParkInstanceAdapter extends RecyclerView.Adapter<CustomNationalParkInstanceAdapter.ViewHolder> {
    private List<NationalParkInstanceInitializer> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            //define click listener for view holder
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
        public TextView getTextView() {
            return textView;
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

    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
