package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.parkfinder.R;
import com.google.android.material.button.MaterialButton;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        MaterialButton btnPay = findViewById(R.id.btnPay);

        btnPay.setOnClickListener(v -> {

            // 1️⃣ Show Processing State
            btnPay.setText("Processing Payment...");
            btnPay.setEnabled(false); // Prevent double tap

            // 2️⃣ Simulate 2-second payment delay
            new Handler(Looper.getMainLooper()).postDelayed(() -> {

                Intent intent = new Intent(
                        PaymentActivity.this,
                        PaymentSuccessActivity.class
                );

                startActivity(intent);
                finish(); // Remove PaymentActivity from back stack

            }, 2000);
        });
    }
}