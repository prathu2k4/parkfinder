package com.example.parkfinder.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.parkfinder.R;
import com.example.parkfinder.activities.AddSpaceStep1Activity;
import com.google.android.material.button.MaterialButton;

public class DashboardHomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_owner_dashboard, container, false);

        MaterialButton btnAddParkingSpaceDash = view.findViewById(R.id.btnAddParkingSpaceDash);

        btnAddParkingSpaceDash.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddSpaceStep1Activity.class);
            startActivity(intent);
        });

        // Setup for rvOwnerSpaces and rvRecentBookings RecyclerViews goes here

        return view;
    }
}