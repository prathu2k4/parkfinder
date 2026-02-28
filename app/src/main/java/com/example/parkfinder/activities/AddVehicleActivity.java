package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.parkfinder.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddVehicleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        // 1. Setup Toolbar Back Button
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // 2. Setup Vehicle Type Dropdown
        String[] vehicleTypes = new String[]{"Car", "Bike"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                vehicleTypes
        );

        AutoCompleteTextView spinnerVehicleType = findViewById(R.id.spinnerVehicleType);
        spinnerVehicleType.setAdapter(adapter);
        spinnerVehicleType.setText(vehicleTypes[0], false); // Default to "Car"

        // 3. Setup Validation & Save Logic
        TextInputEditText etVehicleNumber = findViewById(R.id.etVehicleNumber);
        Button btnSaveVehicle = findViewById(R.id.btnSaveVehicle);

        btnSaveVehicle.setOnClickListener(v -> {

            String vehicleNo = etVehicleNumber.getText() != null
                    ? etVehicleNumber.getText().toString().trim().toUpperCase()
                    : "";

            // Regex: Exactly 10 alphanumeric characters
            if (vehicleNo.matches("^[A-Z0-9]{10}$")) {

                // ✅ Save state in SharedPreferences
                getSharedPreferences("ParkFinderPrefs", MODE_PRIVATE)
                        .edit()
                        .putBoolean("isVehicleAdded", true)
                        .apply();

                Intent intent = new Intent(
                        AddVehicleActivity.this,
                        EnableLocationActivity.class
                );
                startActivity(intent);

                finish(); // Prevent returning to this screen

            } else {
                etVehicleNumber.setError("Must be exactly 10 alphanumeric characters");
            }
        });
    }
}