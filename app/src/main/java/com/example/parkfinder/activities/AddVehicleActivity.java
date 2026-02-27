package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.parkfinder.R;

public class AddVehicleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        // Setup the Vehicle Type Dropdown
        String[] vehicleTypes = new String[]{"Car", "Bike"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                vehicleTypes
        );
        AutoCompleteTextView spinnerVehicleType = findViewById(R.id.spinnerVehicleType);
        spinnerVehicleType.setAdapter(adapter);

        Button btnSaveVehicle = findViewById(R.id.btnSaveVehicle);
        btnSaveVehicle.setOnClickListener(v -> {
            Intent intent = new Intent(AddVehicleActivity.this, EnableLocationActivity.class);
            startActivity(intent);
        });
    }
}