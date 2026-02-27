package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.parkfinder.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button btnContinue = findViewById(R.id.btnContinue);
        Button btnGoogle = findViewById(R.id.btnGoogle);

        // Both buttons currently route to Mode Selection for demonstration
        btnContinue.setOnClickListener(v -> navigateToModeSelection());
        btnGoogle.setOnClickListener(v -> navigateToModeSelection());
    }

    private void navigateToModeSelection() {
        Intent intent = new Intent(WelcomeActivity.this, ModeSelectionActivity.class);
        startActivity(intent);
    }
}