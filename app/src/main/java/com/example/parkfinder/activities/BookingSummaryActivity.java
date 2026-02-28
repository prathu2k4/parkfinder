package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.parkfinder.R;

public class BookingSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_summary);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // ✅ Bind Views
        android.widget.TextView tvSpotName =
                findViewById(R.id.tvSummarySpotName);

        android.widget.TextView tvDuration =
                findViewById(R.id.tvSummaryDuration);

        android.widget.TextView tvTotal =
                findViewById(R.id.tvTotalAmount);

        // ✅ Get Data from Intent
        String name = getIntent().getStringExtra("SPOT_NAME");
        String duration = getIntent().getStringExtra("DURATION");
        String total = getIntent().getStringExtra("TOTAL_COST");

        // ✅ Set Data to UI
        if (name != null) tvSpotName.setText(name);
        if (duration != null) tvDuration.setText(duration);
        if (total != null) tvTotal.setText(total);

        // ✅ Proceed to Payment
        findViewById(R.id.btnProceedPayment).setOnClickListener(v -> {
            startActivity(new Intent(this, PaymentActivity.class));
        });
    }
}