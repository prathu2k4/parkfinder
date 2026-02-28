package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkfinder.R;
import com.google.android.material.button.MaterialButton;

public class PaymentSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        MaterialButton btnViewActive = findViewById(R.id.btnViewActive);
        Button btnBackHome = findViewById(R.id.btnBackHome);
        Button btnDone = findViewById(R.id.btnDone); // ✅ New Button

        btnViewActive.setOnClickListener(v -> {
            Intent intent = new Intent(
                    PaymentSuccessActivity.this,
                    ActiveBookingActivity.class
            );
            startActivity(intent);
            finish();
        });

        btnBackHome.setOnClickListener(v -> {
            Intent intent = new Intent(
                    PaymentSuccessActivity.this,
                    MainActivity.class
            );
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        // ✅ NEW Done Button Logic
        btnDone.setOnClickListener(v -> {

            Intent intent = new Intent(
                    PaymentSuccessActivity.this,
                    MainActivity.class
            );

            // Clear entire back stack
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);

            // Optional: Open My Bookings tab
            intent.putExtra("OPEN_TAB", "BOOKINGS");

            startActivity(intent);
        });
    }
}