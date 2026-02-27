package com.example.parkfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.parkfinder.R;

public class ListHomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_home, container, false);

        ImageButton btnFilterList = view.findViewById(R.id.btnFilterList);

        btnFilterList.setOnClickListener(v -> {
            FiltersBottomSheet bottomSheet = new FiltersBottomSheet();
            bottomSheet.show(getParentFragmentManager(), "FiltersBottomSheet");
        });

        // RecyclerView setup for the parking list goes here

        return view;
    }
}