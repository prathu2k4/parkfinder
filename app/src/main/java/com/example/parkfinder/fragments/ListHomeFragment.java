package com.example.parkfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkfinder.ParkingSpot;
import com.example.parkfinder.ParkingSpotAdapter;
import com.example.parkfinder.ParkingDataManager;
import com.example.parkfinder.R;

import java.util.List;

public class ListHomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_home, container, false);

        // Filter Button
        ImageButton btnFilterList = view.findViewById(R.id.btnFilterList);
        btnFilterList.setOnClickListener(v -> {
            FiltersBottomSheet bottomSheet = new FiltersBottomSheet();
            bottomSheet.show(getParentFragmentManager(), "FiltersBottomSheet");
        });

        // RecyclerView Setup
        RecyclerView rvParkingList = view.findViewById(R.id.rvParkingList);
        rvParkingList.setLayoutManager(new LinearLayoutManager(getContext()));

        // ✅ Use Shared Dynamic Data
        List<ParkingSpot> dynamicSpots =
                ParkingDataManager.getInstance().sharedSpots;

        ParkingSpotAdapter adapter = new ParkingSpotAdapter(dynamicSpots);
        rvParkingList.setAdapter(adapter);

        // ✅ Toggle Buttons
        TextView btnNearest = view.findViewById(R.id.btnSortNearest);
        TextView btnCheapest = view.findViewById(R.id.btnSortCheapest);

        btnCheapest.setOnClickListener(v -> {

            // 1. Highlight Cheapest
            btnCheapest.setBackgroundResource(R.drawable.rounded_bg_teal_outline);
            btnCheapest.setTextColor(getResources().getColor(R.color.teal_main));

            // 2. Un-highlight Nearest
            btnNearest.setBackgroundResource(R.drawable.rounded_bg_light);
            btnNearest.setTextColor(getResources().getColor(R.color.dark_grey));

            // 3. Sort Logic (Cheapest First)
            java.util.Collections.sort(dynamicSpots, (s1, s2) ->
                    Integer.compare(
                            Integer.parseInt(s1.price),
                            Integer.parseInt(s2.price)
                    )
            );

            adapter.notifyDataSetChanged();
        });

        btnNearest.setOnClickListener(v -> {

            // 1. Highlight Nearest
            btnNearest.setBackgroundResource(R.drawable.rounded_bg_teal_outline);
            btnNearest.setTextColor(getResources().getColor(R.color.teal_main));

            // 2. Un-highlight Cheapest
            btnCheapest.setBackgroundResource(R.drawable.rounded_bg_light);
            btnCheapest.setTextColor(getResources().getColor(R.color.dark_grey));

            // 3. Sort Logic (Nearest First)
            java.util.Collections.sort(dynamicSpots, (s1, s2) -> {
                double dist1 = Double.parseDouble(s1.distance.replace(" km", ""));
                double dist2 = Double.parseDouble(s2.distance.replace(" km", ""));
                return Double.compare(dist1, dist2);
            });

            adapter.notifyDataSetChanged();
        });

        return view;
    }
}