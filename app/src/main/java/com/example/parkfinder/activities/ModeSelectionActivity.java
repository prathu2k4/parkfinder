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

        // Rider Click - Fade Transition
        cardRider.setOnClickListener(v -> {
            Intent intent = new Intent(ModeSelectionActivity.this, AddVehicleActivity.class);
            startActivity(intent);
            overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
            );
        });

        // Owner Click - Slide Transition
        cardOwner.setOnClickListener(v -> {
            Intent intent = new Intent(ModeSelectionActivity.this, OwnerDashboardActivity.class);
            startActivity(intent);
            overridePendingTransition(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
            );
        });
    }
}