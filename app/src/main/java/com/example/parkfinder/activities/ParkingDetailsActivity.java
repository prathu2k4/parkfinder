package com.example.parkfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.parkfinder.R;

public class ParkingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Initialize Views
        TextView tvParkingName = findViewById(R.id.tvParkingName);
        TextView tvDetailsPrice = findViewById(R.id.tvDetailsPrice);
        TextView tvDetailsDistance = findViewById(R.id.tvDetailsDistance);

        Button btnBookNow = findViewById(R.id.btnBookNow);

        // Get Data from Intent
        String name = getIntent().getStringExtra("SPOT_NAME");
        String price = getIntent().getStringExtra("SPOT_PRICE");
        String distance = getIntent().getStringExtra("SPOT_DISTANCE");

        // Set Data to Views
        if (name != null) {
            tvParkingName.setText(name);
            tvDetailsPrice.setText("₹" + price + " / hour");
            tvDetailsDistance.setText("📍 " + distance);
        }

        // Book Now Button
        btnBookNow.setOnClickListener(v -> {
            Intent intent = new Intent(ParkingDetailsActivity.this, SelectTimeSlotActivity.class);
            startActivity(intent);
        });
    }
}
