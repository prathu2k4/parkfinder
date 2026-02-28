package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkfinder.R;
import com.google.android.material.textfield.TextInputEditText;

public class WelcomeActivity extends AppCompatActivity {

    private TextInputEditText etLoginId;
    private Button btnContinue;
    private Button btnGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Initialize Views
        etLoginId = findViewById(R.id.etLoginId);
        btnContinue = findViewById(R.id.btnContinue);
        btnGoogle = findViewById(R.id.btnGoogle);

        // Continue Button Click
        btnContinue.setOnClickListener(v -> {

            String input = etLoginId.getText() != null
                    ? etLoginId.getText().toString().trim()
                    : "";

            // Validate Email OR 10-digit Phone Number
            boolean isEmail = Patterns.EMAIL_ADDRESS.matcher(input).matches();
            boolean isPhone = input.matches("^[0-9]{10}$");

            if (isEmail || isPhone) {

                // ✅ Check if vehicle already added
                boolean hasVehicle = getSharedPreferences(
                        "ParkFinderPrefs",
                        MODE_PRIVATE
                ).getBoolean("isVehicleAdded", false);

                if (hasVehicle) {
                    // Skip onboarding → Go directly to Main App
                    startActivity(new Intent(
                            WelcomeActivity.this,
                            MainActivity.class
                    ));
                } else {
                    // Go to Mode Selection
                    startActivity(new Intent(
                            WelcomeActivity.this,
                            ModeSelectionActivity.class
                    ));
                }

                finish(); // Prevent going back to login

            } else {
                etLoginId.setError("Enter a valid 10-digit phone number or email");
                etLoginId.requestFocus();
            }
        });

        // Google Button (same logic as Continue)
        btnGoogle.setOnClickListener(v -> {

            boolean hasVehicle = getSharedPreferences(
                    "ParkFinderPrefs",
                    MODE_PRIVATE
            ).getBoolean("isVehicleAdded", false);

            if (hasVehicle) {
                startActivity(new Intent(
                        WelcomeActivity.this,
                        MainActivity.class
                ));
            } else {
                startActivity(new Intent(
                        WelcomeActivity.this,
                        ModeSelectionActivity.class
                ));
            }

            finish();
        });
    }
}