package com.example.locationapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NationalParkListAdapter extends RecyclerView.Adapter<com.example.locationapp.CustomNationalParkInstanceAdapter.ViewHolder> {
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
        public com.example.locationapp.CustomNationalParkInstanceAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            //create a new view which defines the ui of the list item
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.np_main_row, viewGroup, false);
            return new com.example.locationapp.CustomNationalParkInstanceAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull com.example.locationapp.CustomNationalParkInstanceAdapter.ViewHolder viewHolder, final int position) {
            NationalParkInstance nationalParkInstance = nationalParkInstances.get(position);

            for(NationalPark nationalPark : nationalParks) {
                if(nationalPark.getUid() == nationalParkInstance.getParkId()) {
                    viewHolder.getTextView().setText(nationalPark.getParkName());

                    TextView parkTextView = viewHolder.getTextView();
                    parkTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mainActivity.setNationalParkScreen(nationalPark, nationalParkInstance);
                        }
                    });

                    if(nationalParkInstance.isHasCompleted()) {
                        viewHolder.getTextView().setTextColor(Color.GREEN);
                    }
                    else if(nationalParkInstance.isHasVisited()) {
                        viewHolder.getTextView().setTextColor(Color.YELLOW);
                    }
                    else {
                        viewHolder.getTextView().setTextColor(Color.LTGRAY);
                    }
                }
            }
        }

        @Override
        public int getItemCount() {
            return nationalParkInstances.size();
        }




}
