package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.parkfinder.R;

public class EnableLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_location);

        Button btnAllowLocation = findViewById(R.id.btnAllowLocation);

        btnAllowLocation.setOnClickListener(v -> {
            // Onboarding complete! Navigate to the main Rider Map interface.
            // Note: Real implementation would request Android location permissions here first.
            Intent intent = new Intent(EnableLocationActivity.this, MainActivity.class);
            // Clear the back stack so the user can't hit 'back' to return to onboarding
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}