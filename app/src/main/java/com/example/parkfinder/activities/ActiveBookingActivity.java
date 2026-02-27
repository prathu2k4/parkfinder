package com.example.parkfinder.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.parkfinder.R;
import com.google.android.material.button.MaterialButton;

public class ActiveBookingActivity extends AppCompatActivity {

    private TextView tvTimer;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_booking);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // We will mock a 4-hour countdown (14,400,000 milliseconds)
        long timeLeftInMillis = 14400000;

        // Note: In a real app, you would target the specific TextView ID from your layout
        // Assuming you give your large timer text an ID of tvCountdownTimer
        // tvTimer = findViewById(R.id.tvCountdownTimer);

        MaterialButton btnCancelBooking = findViewById(R.id.btnCancelBooking);
        btnCancelBooking.setOnClickListener(v -> {
            Toast.makeText(this, "Booking Cancelled", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}