package com.example.parkfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.parkfinder.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

public class FiltersBottomSheet extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_filters, container, false);

        ImageButton btnCloseFilter = view.findViewById(R.id.btnCloseFilter);
        Button btnApplyFilters = view.findViewById(R.id.btnApplyFilters);
        Button btnResetFilters = view.findViewById(R.id.btnResetFilters);

        Slider sliderDistance = view.findViewById(R.id.sliderDistance);
        RangeSlider rangeSliderPrice = view.findViewById(R.id.rangeSliderPrice);

        // You will need to add these IDs to your TextViews in bottom_sheet_filters.xml
        // e.g., <TextView android:id="@+id/tvDistanceLabel" android:text="Distance: 15km" ... />
        // TextView tvDistanceLabel = view.findViewById(R.id.tvDistanceLabel);
        // TextView tvPriceLabel = view.findViewById(R.id.tvPriceLabel);

        // Live update distance slider
        sliderDistance.addOnChangeListener((slider, value, fromUser) -> {
            // tvDistanceLabel.setText("Distance: Within " + (int)value + " km");
        });

        // Live update range slider
        rangeSliderPrice.addOnChangeListener((slider, value, fromUser) -> {
            int min = Math.round(slider.getValues().get(0));
            int max = Math.round(slider.getValues().get(1));
            // tvPriceLabel.setText("Price Range: ₹" + min + " - ₹" + max);
        });

        btnCloseFilter.setOnClickListener(v -> dismiss());

        btnApplyFilters.setOnClickListener(v -> dismiss());

        btnResetFilters.setOnClickListener(v -> {
            sliderDistance.setValue(15.0f);
            rangeSliderPrice.setValues(0.0f, 200.0f);
        });

        return view;
    }
}