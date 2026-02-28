package com.example.parkfinder.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parkfinder.R;

public class EnableLocationActivity extends AppCompatActivity {

    // Permission Launcher (MUST be declared before onCreate)
    private final ActivityResultLauncher<String[]> requestPermissionLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.RequestMultiplePermissions(),
                    result -> {
                        Boolean fineLocationGranted =
                                result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);

                        if (fineLocationGranted != null && fineLocationGranted) {

                            // Permission granted → Move to MainActivity
                            Intent intent = new Intent(
                                    EnableLocationActivity.this,
                                    MainActivity.class
                            );
                            intent.setFlags(
                                    Intent.FLAG_ACTIVITY_NEW_TASK |
                                            Intent.FLAG_ACTIVITY_CLEAR_TASK
                            );
                            startActivity(intent);

                        } else {
                            Toast.makeText(
                                    this,
                                    "Location permission is required to find parking.",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_location);

        Button btnAllowLocation = findViewById(R.id.btnAllowLocation);

        // Launch permission request on button click
        btnAllowLocation.setOnClickListener(v -> {
            requestPermissionLauncher.launch(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });
        });
    }
}