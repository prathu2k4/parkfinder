package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.parkfinder.R;

public class SelectTimeSlotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time_slot);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Button btnContinueToSummary = findViewById(R.id.btnContinueToSummary);

        // In a real app, you would validate the time pickers here before allowing continuation
        btnContinueToSummary.setOnClickListener(v -> {
            Intent intent = new Intent(SelectTimeSlotActivity.this, BookingSummaryActivity.class);
            startActivity(intent);
        });
    }
}