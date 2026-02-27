package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.parkfinder.R;

public class ModeSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selection);

        CardView cardRider = findViewById(R.id.cardRider);
        CardView cardOwner = findViewById(R.id.cardOwner);

        cardRider.setOnClickListener(v -> {
            // Rider onboarding continues with adding a vehicle
            Intent intent = new Intent(ModeSelectionActivity.this, AddVehicleActivity.class);
            startActivity(intent);
        });

        cardOwner.setOnClickListener(v -> {
            // Owner skips onboarding and goes straight to their dashboard
            Intent intent = new Intent(ModeSelectionActivity.this, OwnerDashboardActivity.class);
            startActivity(intent);
        });
    }
}