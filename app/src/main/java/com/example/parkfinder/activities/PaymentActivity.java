package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.parkfinder.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Button btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentActivity.this, PaymentSuccessActivity.class);
            startActivity(intent);
            finish(); // Close payment screen so user can't hit back to it
        });
    }
}