package com.example.parkfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.parkfinder.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FiltersBottomSheet extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_filters, container, false);

        ImageButton btnCloseFilter = view.findViewById(R.id.btnCloseFilter);
        Button btnApplyFilters = view.findViewById(R.id.btnApplyFilters);

        btnCloseFilter.setOnClickListener(v -> dismiss());

        btnApplyFilters.setOnClickListener(v -> {
            // Apply filter logic here
            dismiss();
        });

        return view;
    }
}