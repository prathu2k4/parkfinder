package com.example.parkfinder.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.parkfinder.R;

public class AddVehicleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        // 1. Setup Dropdown
        String[] vehicleTypes = new String[]{"Car", "Bike"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, vehicleTypes);
        AutoCompleteTextView spinnerVehicleType = findViewById(R.id.spinnerVehicleType);
        spinnerVehicleType.setAdapter(adapter);

        // 2. Setup Custom Toggle Logic
        RadioGroup rgVehicleCategory = findViewById(R.id.rgVehicleCategory);
        RadioButton rbCar = findViewById(R.id.rbCar);
        RadioButton rbBike = findViewById(R.id.rbBike);

        rgVehicleCategory.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbCar) {
                // Active State for Car
                rbCar.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_main));
                rbCar.setTextColor(Color.WHITE);
                // Inactive State for Bike
                rbBike.setBackgroundColor(Color.TRANSPARENT);
                rbBike.setTextColor(ContextCompat.getColor(this, R.color.dark_grey));
            } else if (checkedId == R.id.rbBike) {
                // Active State for Bike
                rbBike.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_main));
                rbBike.setTextColor(Color.WHITE);
                // Inactive State for Car
                rbCar.setBackgroundColor(Color.TRANSPARENT);
                rbCar.setTextColor(ContextCompat.getColor(this, R.color.dark_grey));
            }
        });

        // Trigger default state
        rbCar.setChecked(true);

        // 3. Setup Navigation
        Button btnSaveVehicle = findViewById(R.id.btnSaveVehicle);
        btnSaveVehicle.setOnClickListener(v -> {
            Intent intent = new Intent(AddVehicleActivity.this, EnableLocationActivity.class);
            startActivity(intent);
        });
    }
}