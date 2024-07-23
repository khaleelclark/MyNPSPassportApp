package com.example.locationapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NationalParkListAdapter extends RecyclerView.Adapter<com.example.locationapp.NationalParkListAdapter.ViewHolder> {
    private final MainActivity mainActivity;
    private List<NationalParkInstance> nationalParkInstances;
     private List<NationalPark> nationalParks;

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
        }

        //initialize dataset
        public NationalParkListAdapter(List<NationalParkInstance> nationalParkInstances, List<NationalPark> nationalParks, MainActivity mainActivity) {
            this.nationalParkInstances = nationalParkInstances;
            this.nationalParks = nationalParks;
            this.mainActivity = mainActivity;
        }

        @Override
        public com.example.locationapp.NationalParkListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            //create a new view which defines the ui of the list item
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.np_main_row, viewGroup, false);
            return new com.example.locationapp.NationalParkListAdapter.ViewHolder(view);
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public void onBindViewHolder(@NonNull @NotNull com.example.locationapp.NationalParkListAdapter.ViewHolder viewHolder, final int position) {
            NationalParkInstance nationalParkInstance = nationalParkInstances.get(position);

            for(NationalPark nationalPark : nationalParks) {
                if(nationalPark.getUid() == nationalParkInstance.getParkId()) {
                    viewHolder.getTextView().setText(nationalPark.getParkName());

                    TextView parkTextView = viewHolder.getTextView();
                    parkTextView.setOnClickListener(v -> mainActivity.setNationalParkScreen(nationalPark, nationalParkInstance));

                    if(nationalParkInstance.isHasCompleted()) {
                        viewHolder.getTextView().setTextColor(Color.GREEN);
                    }
                    else if(nationalParkInstance.isHasVisited()) {
                        viewHolder.getTextView().setTextColor(Color.YELLOW);
                    }
                    else {
                        viewHolder.getTextView().setTextColor(Color.LTGRAY);
                    }
                    if(nationalPark.getParkName().equals("Canyonlands")) {
                        viewHolder.getTextView().setBackground(mainActivity.getDrawable(R.drawable.desert));
                    }
                    if(nationalPark.getParkName().equals("Bryce Canyon")) {
                        viewHolder.getTextView().setBackground(mainActivity.getDrawable(R.drawable.bryce_canyon_np));
                    }
                    if(nationalPark.getParkName().equals("Arches")) {
                        viewHolder.getTextView().setBackground(mainActivity.getDrawable(R.drawable.arches_np));
                    }
                   if(nationalPark.getParkName().equals("Zion")) {
                        viewHolder.getTextView().setBackground(mainActivity.getDrawable(R.drawable.zion_np));
                    }
                   if(nationalPark.getParkName().equals("Grand Teton")) {
                        viewHolder.getTextView().setBackground(mainActivity.getDrawable(R.drawable.grand_teton_np));
                    }
                    if(nationalPark.getParkName().equals("Capitol Reef")) {
                        viewHolder.getTextView().setBackground(mainActivity.getDrawable(R.drawable.capitol_reef_np));
                    }
                }
            }
        }
        @Override
        public int getItemCount() {
            return nationalParkInstances.size();
        }
}
